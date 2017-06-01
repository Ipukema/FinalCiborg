package proyect.robots.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import proyect.robots.entities.player.Player.ShootingDir;
import proyect.robots.utils.CustomAnim;

public class PlayerMovementController {	
	public Player player;
	
	public PlayerMovementController(Player player){
		this.player=player;					
	}
	
	public void moveOnLeftSide(float delta){
		if (Gdx.input.isKeyPressed(Input.Keys.X)){
			player.body.applyForceToCenter(0, -Player.GRAVITY, true);
			player.body.setAwake(false);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){				
				if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)){
					player.sdir=ShootingDir.U;
					player.curanim=CustomAnim.getById(player.anims, "holdRSU");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
					player.sdir=ShootingDir.D;
					player.curanim=CustomAnim.getById(player.anims, "holdRSD");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
					player.sdir=ShootingDir.LU;
					player.curanim=CustomAnim.getById(player.anims, "holdRSUR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
					player.sdir=ShootingDir.LD;
					player.curanim=CustomAnim.getById(player.anims, "holdRSDR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
					player.sdir=ShootingDir.RU;
					player.curanim=CustomAnim.getById(player.anims, "holdRSUL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
					player.sdir=ShootingDir.RD;
					player.curanim=CustomAnim.getById(player.anims, "holdRSDL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
					player.sdir=ShootingDir.R;
					player.curanim=CustomAnim.getById(player.anims, "holdRSL");
				}else {
					if (player.dir==Player.RIGHT ){
						player.sdir=ShootingDir.R;
						player.curanim=CustomAnim.getById(player.anims, "holdRSR");
					}else if(player.dir==Player.LEFT){
						player.sdir=ShootingDir.L;
						player.curanim=CustomAnim.getById(player.anims, "holdRSL");
					}
				}
				player.addShoot();
			}
		}else if((Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8))){
			player.state=Player.RUN;			
			player.body.setLinearVelocity(0, Player.CLIMBSPEED);
			player.curanim=CustomAnim.getById(player.anims, "climbR");				
		}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_2)){
			player.state=Player.RUN;	
			player.body.setLinearVelocity(0, -Player.CLIMBSPEED);
			player.curanim=CustomAnim.getById(player.anims, "climbR");
		}else if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || (!Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
			player.body.applyForceToCenter(0, -Player.GRAVITY, true);
			player.body.setAwake(false);
			player.curanim=CustomAnim.getById(player.anims, "holdLSL");
			player.dir=Player.LEFT;
			player.sdir=ShootingDir.R;
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}	
		if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
		 	player.body.setTransform(player.body.getPosition().x+0.01f , (player.body.getPosition().y),0);
			player.body.setLinearVelocity(player.caer);
			player.curanim=CustomAnim.getById(player.anims, "jumpR");			
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
			player.body.setLinearVelocity(Player.MAX_JUMP_SPEED,Player.MAX_JUMP_SPEED);
			player.curanim=CustomAnim.getById(player.anims, "jumpR");		
		}
	}
	
	public void moveOnRightSide(float delta){		
		if (Gdx.input.isKeyPressed(Input.Keys.X)){
			player.body.applyForceToCenter(0, -Player.GRAVITY, true);
			player.body.setAwake(false);
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)){
					player.sdir=ShootingDir.U;
					player.curanim=CustomAnim.getById(player.anims, "holdRSU");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
					player.sdir=ShootingDir.D;
					player.curanim=CustomAnim.getById(player.anims, "holdRSD");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
					player.sdir=ShootingDir.LU;
					player.curanim=CustomAnim.getById(player.anims, "holdRSUL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
					player.sdir=ShootingDir.LD;
					player.curanim=CustomAnim.getById(player.anims, "holdRSDL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
					player.sdir=ShootingDir.RU;
					player.curanim=CustomAnim.getById(player.anims, "holdRSUR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
					player.sdir=ShootingDir.RD;
					player.curanim=CustomAnim.getById(player.anims, "holdRSDR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
					player.sdir=ShootingDir.L;
					player.curanim=CustomAnim.getById(player.anims, "holdRSL");
				}else {
					if (player.dir==Player.RIGHT ){
						player.sdir=ShootingDir.R;
						player.curanim=CustomAnim.getById(player.anims, "holdRSR");
					}else if(player.dir==Player.LEFT){
						player.sdir=ShootingDir.L;
						player.curanim=CustomAnim.getById(player.anims, "holdRSL");
					}
				}
				player.addShoot();
			}			
		}else if((Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8))){
			player.state=Player.RUN;	
			player.body.setLinearVelocity(0, Player.CLIMBSPEED);
			player.curanim=CustomAnim.getById(player.anims, "climbR");				
		}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
			player.state=Player.RUN;	
			player.body.setLinearVelocity(0,-Player.CLIMBSPEED);
			player.curanim=CustomAnim.getById(player.anims, "climbR");			
		}else if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || (!Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
			player.body.setAwake(false);
			player.state=Player.STAND;
			if(player.dir==Player.RIGHT){
				player.curanim=CustomAnim.getById(player.anims, "holdRSR");
				player.sdir=ShootingDir.R;
			}else if(player.dir==Player.LEFT){
				player.curanim=CustomAnim.getById(player.anims, "holdLSL");
				player.sdir=ShootingDir.L;
			}
			player.body.setLinearVelocity(0, 0);
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}	
		if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
			player.body.setTransform(player.body.getPosition().x-0.01f , (player.body.getPosition().y),0);
			player.body.setLinearVelocity(player.caer);
			player.curanim=CustomAnim.getById(player.anims, "jumpR");			
		}else if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
			player.body.setLinearVelocity(-Player.MAX_JUMP_SPEED,Player.MAX_JUMP_SPEED);
			player.curanim=CustomAnim.getById(player.anims, "jumpR");		
		}		
	}	
	
	public void moveOnCeiling(float dt){		
		if (Gdx.input.isKeyPressed(Input.Keys.X)){		
			player.body.applyForceToCenter(0, -Player.GRAVITY, true);
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.SPACE)){
				if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)){
					player.sdir=ShootingDir.U;
					player.curanim=CustomAnim.getById(player.anims, "holdUSUfaceR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
					player.sdir=ShootingDir.D;
					player.curanim=CustomAnim.getById(player.anims, "holdUSDfaceR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
					player.dir=Player.LEFT;
					player.sdir=ShootingDir.LU;
					player.curanim=CustomAnim.getById(player.anims, "holdUSUL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
					player.dir=Player.LEFT;
					player.sdir=ShootingDir.LD;
					player.curanim=CustomAnim.getById(player.anims, "holdUSDL");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
					player.dir=Player.RIGHT;
					player.sdir=ShootingDir.RU;
					player.curanim=CustomAnim.getById(player.anims, "holdUSUR");
				}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
					player.dir=Player.RIGHT;
					player.sdir=ShootingDir.RD;
					player.curanim=CustomAnim.getById(player.anims, "holdUSDR");
				}else {
					if (player.dir==Player.RIGHT){
						player.sdir=ShootingDir.R;
						player.curanim=CustomAnim.getById(player.anims, "holdUSR");
					}else if(player.dir==Player.LEFT){
						player.sdir=ShootingDir.L;
						player.curanim=CustomAnim.getById(player.anims, "holdUSL");
					}
				}
				player.addShoot();
			}
		}else if((Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4))){
			player.state=Player.RUN;
			player.dir=Player.LEFT;						
			player.sdir=ShootingDir.L;
			player.body.setLinearVelocity(-Player.CLIMBSPEED*2,-Player.GRAVITY);
			player.curanim=CustomAnim.getById(player.anims, "holdUmoveR");			
		}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)){
			player.state=Player.RUN;
			player.dir=Player.RIGHT;
			player.sdir=ShootingDir.R;
			player.body.setLinearVelocity(Player.CLIMBSPEED*2,-Player.GRAVITY);
			player.curanim=CustomAnim.getById(player.anims, "holdUmoveR");			
		}else if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || (!Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
			player.state=Player.STAND;
			player.body.setLinearVelocity(0,-Player.GRAVITY);
			if(player.dir==Player.LEFT){
				player.curanim=CustomAnim.getById(player.anims, "holdUSL");
			}else{
				player.curanim=CustomAnim.getById(player.anims, "holdUSR");
			}			
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
			 player.body.setLinearVelocity(player.caer);	
		 }	
	}
	
	public void moveOnGround(float dt){
		 if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT) && 
				(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))){
			if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)){
				player.sdir=ShootingDir.U;
				player.curanim=CustomAnim.getById(player.anims, "standRSU");
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)){
				player.sdir=ShootingDir.D;
				player.curanim=CustomAnim.getById(player.anims, "standRSD");
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
				player.dir=Player.LEFT;
				player.sdir=ShootingDir.LU;
				player.curanim=CustomAnim.getById(player.anims, "standRSUR");
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
				player.dir=Player.LEFT;
				player.sdir=ShootingDir.LD;
				player.curanim=CustomAnim.getById(player.anims, "standRSDR");
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
				player.dir=Player.RIGHT;
				player.sdir=ShootingDir.RU;
				player.curanim=CustomAnim.getById(player.anims, "standRSUR");
			}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
				player.dir=Player.RIGHT;
				player.sdir=ShootingDir.RD;
				player.curanim=CustomAnim.getById(player.anims, "standRSDR");
			}else {
				if (player.dir==Player.RIGHT){
					player.sdir=ShootingDir.R;
					player.curanim=CustomAnim.getById(player.anims, "standRSR");
				}else if(player.dir==Player.LEFT){
					player.sdir=ShootingDir.L;
					player.curanim=CustomAnim.getById(player.anims, "standRSR");
				}
			}
			player.addShoot();
		}else if((Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4))){	
				player.state=Player.RUN;
				player.dir=Player.LEFT;						
				player.sdir=ShootingDir.L;
				player.body.setLinearVelocity(-Player.SPEED, 0);
				player.curanim=CustomAnim.getById(player.anims, "runLSL");
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}else if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6) || Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_6)){
			player.state=Player.RUN;
			player.dir=Player.RIGHT;
			player.sdir=ShootingDir.R;
			player.body.setLinearVelocity(Player.SPEED, 0);
			player.curanim=CustomAnim.getById(player.anims, "runRSR");			
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}else if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || (!Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))) {
			player.state=Player.STAND;
			player.curanim=CustomAnim.getById(player.anims, "stand");
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				player.addShoot();
			}
		}	
		 if(player.action!=Player.JUMP){
			 if(Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
					player.action = Player.JUMP;
					player.body.setLinearVelocity(Player.MAX_JUMP_SPEED/3,Player.MAX_JUMP_SPEED);			 
			 }else if(Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
					player.action = Player.JUMP;
					player.body.setLinearVelocity(-Player.MAX_JUMP_SPEED/3,Player.MAX_JUMP_SPEED);
			}else if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
					player.action = Player.JUMP;
					player.body.setLinearVelocity(0,Player.MAX_JUMP_SPEED);					
			}
		 }		
	}	
}