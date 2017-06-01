package proyect.robots.screens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.MyGame;
import proyect.robots.entities.Drop;
import proyect.robots.entities.Png;
import proyect.robots.entities.Enemies.BigRobot;
import proyect.robots.entities.Enemies.Drone1;
import proyect.robots.entities.Enemies.Drone2;
import proyect.robots.entities.Enemies.FinalBoss1;
import proyect.robots.entities.Enemies.FocusedShoot;
import proyect.robots.utils.Crect;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.SoundAssets;
import proyect.robots.entities.Enemies.BigRobot.ShootingDir;
import proyect.robots.entities.player.GuidedShoot;
import proyect.robots.entities.player.Player;
import proyect.robots.entities.player.Shoot;

public class MyRenderer {
	
	public PlayScreen screen;
	public Sound playerShootHit, playerMisileHit;
	
	public MyRenderer(PlayScreen screen){
		this.screen=screen;
		playerShootHit = SoundAssets.playerShootHit;
		playerMisileHit = SoundAssets.playerMisileHit;
	}
	
	public void renderPlayer(float delta, float elapsedTime, SpriteBatch batch){                                                                  
		Player player=screen.getPlayer();
		float x= player.body.getPosition().x*100;
		float y= player.body.getPosition().y*100;
		batch.begin(); 
		//Draw the player:
		TextureRegion reg = player.curanim.getKeyFrame(elapsedTime, true);
		
		boolean flip = (player.dir==Player.LEFT);
		boolean pos = (player.position==Player.GROUND);
		
		batch.draw(reg, flip ? x+player.width/2 : x-player.width/2 ,pos ? y-player.height/2-2:y-player.height/2+2 
				,flip ? -player.width : player.width, player.height);
		
		if (Player.shielded){
			batch.draw(player.shield.getKeyFrame(delta, true), player.getX() , flip ? player.getY()-12:player.getY()+8,0,0,20,8,1,1,flip ? 90 : 270);
		}		
		//Draw Player shoots:
		for(Iterator<Shoot> itr = Player.bullets.iterator(); itr.hasNext(); ){
			Object s= itr.next();
			if(s.getClass().equals(Shoot.class)){
				Shoot b = (Shoot) s;
				b.Update(delta);			
				b.pea.draw(batch, delta);
				for(Array<Crect> recs: screen.mapBounds){
					for(Crect r: recs){
						if(r.overlaps(b.bounds)){
							Player.remBull.add(b);
						}
					}
				}
				for(Png en:screen.enemies1){
					if(b.bounds.overlaps(en.bounds) && !en.isDied()){					
						  	PlayScreen.score+=5;
						  	en.getHit(20);
						  	playerShootHit.play(0.5f*MyGame.fxValue);
							Player.remBull.add(b);
							batch.draw(LoadResources.greenEnergy.getKeyFrame(elapsedTime), en.getX()+en.bounds.getWidth()/2, en.getY(),
									en.bounds.getWidth(), en.bounds.getHeight());
						  	if(en.isDied()){
						  		PlayScreen.score+=20;
						  		new Drop(screen.crafting, new Vector2(en.getX(), en.getY()), delta);
							}
							
				}else{
					batch.draw(b.bullet,b.getX(),b.getY(),b.bullet.getHeight()/3,b.bullet.getWidth()/3);	
				}				
			  }					
				batch.draw(b.bullet,b.getX(),b.getY(),b.bullet.getHeight()/3,b.bullet.getWidth()/3);
			}else if(s.getClass().equals(GuidedShoot.class)){
				GuidedShoot b = (GuidedShoot) s;
				b.Update(delta);				
				b.pea.draw(batch, delta);
				for(Array<Crect> recs: screen.mapBounds){
					for(Crect r: recs){
						if(r.overlaps(b.bounds)){
							Player.remBull.add(b);
						}						
					}
				}
				for(Png en:screen.enemies1){
					if(b.bounds.overlaps(en.bounds) && !en.isDied()){					
						PlayScreen.score+=5;
							en.getHit(30);
							batch.draw(LoadResources.fireSmoke.getKeyFrame(elapsedTime), en.getX()+en.bounds.getWidth()/2, en.getY(), en.bounds.getWidth(), en.bounds.getHeight());
							playerMisileHit.play(0.8f*MyGame.fxValue);
							Player.remBull.add(b);
							if(en.isDead()){
								PlayScreen.score+=20;
								new Drop(screen.crafting, new Vector2(en.getX(), en.getY()), delta);
								Player.remBull.add(b);
							}
							
					}else{		
						// TODO
						//batch.draw(b.bullet, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
						batch.draw(b.bullet, b.getX(), b.getY(),
								(float)b.bullet.getRegionWidth()/6,0,
								(float)b.bullet.getRegionWidth()/3,(float)b.bullet.getRegionHeight()/3,
								1f,1f, (float)b.correctAngle() );
						//System.out.println("angulo : "+b.correctAngle());
						//batch.draw(b.bullet,b.getX(),b.getY(),b.bullet.getHeight()/3,b.bullet.getWidth()/3);	
					}				
				}
			}			
		}	
		Player.bullets.removeAll(Player.remBull);
		Player.remBull.clear();
		batch.end();
	}
	
	public void renderItems(float delta, SpriteBatch batch){
		ArrayList<Drop> crafting=screen.crafting;
		batch.begin();
		if(crafting.size()!=0){
			for(Iterator<Drop> itr = crafting.iterator(); itr.hasNext();){
				Drop d = (Drop)itr.next();
				d.update(delta);
				batch.draw(d.icon, d.getX(), d.getY(), 8, 8);
				if(d.getDelta()>=d.delete){
					itr.remove();
				}				
			}			
		}
		batch.end();
	}
	
	public void renderLvlOneEnemies(float delta, float elapsedTime, SpriteBatch batch){
		ArrayList<Png> enemies =screen.enemies1;
		
		for(Png w:enemies){
			batch.begin();        
			if (w.getClass().equals(BigRobot.class)){
				TextureRegion regr = w.getCurrentAnim().getKeyFrame(elapsedTime, true);
				boolean flipr = (((BigRobot)w).sdir==ShootingDir.L);
				if(w.died){
					batch.draw(regr, flipr ? w.getX() + 24 : w.getX() -8 ,w.getY()-8,flipr ? -24 : 32, 32);
				}else{
					batch.draw(regr, flipr ? w.getX() + 24 : w.getX() ,w.getY(),flipr ? -24 : 24, 24);
				}
				for (Iterator<FocusedShoot> itr1 = ((BigRobot)w).shoots.iterator(); itr1.hasNext();){
					  FocusedShoot s=itr1.next();
					  if(!PlayScreen.onpause){
						  s.Update(delta); 
					  }
					  if(s.contactWorld ){			  
						    itr1.remove();
					  }else if(s.bounds.overlaps(screen.getPlayer().bounds)){
						  if(!Player.shielded){
							  Player.hurtPlayer(2);
						  }						 	
						 	itr1.remove();
					  }
					  batch.draw(s.bullet, s.getX(),s.getY(),s.bullet.getHeight()/2,s.bullet.getWidth()/2);
				  }
			}else if (w.getClass().equals(Drone1.class)){
				TextureRegion regr = w.getCurrentAnim().getKeyFrame(elapsedTime, true);
				boolean flipr = (((Drone1)w).sdir==Drone1.ShootingDir.L);
				if(w.died){
					System.out.println("Ahora si que si ha muerto");
					batch.draw(regr, flipr ? w.getX() + 24 : w.getX() -8 ,w.getY()-8,flipr ? -24 : 32, 32);
				}else{
					batch.draw(regr, flipr ? w.getX() + 24 : w.getX() ,w.getY(),flipr ? -24 : 24, 24);
				}
				for (Iterator<FocusedShoot> itr1 = ((Drone1)w).shoots.iterator(); itr1.hasNext();){
					  FocusedShoot s=itr1.next();
					  if(!PlayScreen.onpause){
						  s.Update(delta); 
					  }
					  if(s.contactWorld ){			  
						    itr1.remove();
					  }else if(s.bounds.overlaps(screen.getPlayer().bounds)){
						 	//batch.draw(worm.blood.getKeyFrame(elapsedTime,true),worm.getX(), worm.getY(), 16,16);
							//enemieBatch.setColor(batchColor);
					  }
					  batch.draw(s.bullet, s.getX(),s.getY(),s.bullet.getHeight()/2,s.bullet.getWidth()/2);
				}			
		}else if (w.getClass().equals(Drone2.class)){
			TextureRegion regr = w.getCurrentAnim().getKeyFrame(elapsedTime, true);
			boolean flipr = (((Drone2)w).sdir==Drone2.ShootingDir.L);
			batch.draw(regr, flipr ? w.getX() + 16 : w.getX() ,w.getY(),flipr ? -16 : 16, 16);
			for (Iterator<FocusedShoot> itr1 = ((Drone2)w).shoots.iterator(); itr1.hasNext();){
				  FocusedShoot s=itr1.next();
				  if(!PlayScreen.onpause){
					  s.Update(delta); 
				  }
				  if(s.contactWorld ){			  
					    itr1.remove();
				  }else if(s.bounds.overlaps(screen.getPlayer().bounds)){
					 	//batch.draw(worm.blood.getKeyFrame(elapsedTime,true),worm.getX(), worm.getY(), 16,16);
						//enemieBatch.setColor(batchColor);
				  }
				  batch.draw(s.bullet, s.getX(),s.getY(),s.bullet.getHeight()/2,s.bullet.getWidth()/2);
			}		// TODO	
		}else if (w.getClass().equals(FinalBoss1.class)){
			TextureRegion regr = w.getCurrentAnim().getKeyFrame(elapsedTime, true);
			boolean flipr = (((FinalBoss1)w).sdir==FinalBoss1.ShootingDir.R);
			//System.out.println("bolean LEFT BOSS: "+flipr);
			batch.draw(regr, flipr ? w.getX() + 16 : w.getX() ,w.getY(),flipr ? -64 : 64, 64);
			for (Iterator<FocusedShoot> itr1 = ((FinalBoss1)w).shoots.iterator(); itr1.hasNext();){
				  FocusedShoot s=itr1.next();
				  if(!PlayScreen.onpause){
					  s.Update(delta); 
				  }
				  if(s.contactWorld ){			  
					    itr1.remove();
				  }else if(s.bounds.overlaps(screen.getPlayer().bounds)){
					 	//batch.draw(worm.blood.getKeyFrame(elapsedTime,true),worm.getX(), worm.getY(), 16,16);
						//enemieBatch.setColor(batchColor);
				  }
				  batch.draw(s.bullet, s.getX(),s.getY(),s.bullet.getHeight()/2,s.bullet.getWidth()/2);
			}			
		}
		batch.end();
		}
	}
}
