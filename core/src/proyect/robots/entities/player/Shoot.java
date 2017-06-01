package proyect.robots.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.utils.Crect;
import proyect.robots.utils.ParticleEffectActor;

public class Shoot extends Sprite{
	
	public float SPEED = 0.5f;	
	public Vector2 pos, posIni, speed;
	public boolean contactWorld, contactEnemy;
    
	public Rectangle bounds;
    public Texture bullet;
    public Player player;
    
    public ParticleEffect pe;    
    public ParticleEffectActor pea ;
    
    public Shoot (Player player, String type, String particle){
    	this.player=player;
    	bullet = new Texture (type);
    	bullet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	swithcShootDir();
    	bounds = new Rectangle(player.pos.x, player.pos.y, bullet.getWidth()/3, bullet.getHeight()/3); 
    	posIni= new Vector2(player.bounds.getX()+4, player.bounds.getY()+9);
    	pos = new Vector2(player.bounds.getX()+4, player.bounds.getY()+9);
   
    	    	
    	pe = new ParticleEffect();
		pe.load(Gdx.files.internal("Particles/"+particle),Gdx.files.internal(""));
		Array<ParticleEmitter> emitters = pe.getEmitters();
		float targetAngle =0;

		for (int i = 0; i < emitters.size; i++) {                          
			ScaledNumericValue angle = emitters.get(i).getAngle();
			float angleHighMin = angle.getHighMin();
			float angleHighMax = angle.getHighMax();
			float spanHigh = angleHighMax - angleHighMin;
		
			angle.setHigh(targetAngle - spanHigh / 2.0f, targetAngle + spanHigh / 2.0f);
			
			float angleLowMin = angle.getLowMin();
			float angleLowMax = angle.getLowMax();
			float spanLow = angleLowMax - angleLowMin;
			angle.setLow(targetAngle - spanLow / 2.0f, targetAngle + spanLow / 2.0f);
		}
	    pe.scaleEffect(0.1f);
	    pe.allowCompletion();
		pea = new ParticleEffectActor(pe, this, new Vector2(1.5f,1.5f));
	    pea.start();
    }
    
    public void swithcShootDir(){
    	switch(player.sdir){
    	case R:
    		speed=new Vector2(SPEED,0);
    		break;
    	case L:
    		speed=new Vector2(-SPEED,0);
    		break;
    	case D:
    		speed=new Vector2(0,-SPEED);
    		break;
    	case U:
    		speed=new Vector2(0,SPEED);
    		break;
    	case LD:
    		speed=new Vector2(-SPEED,-SPEED);
    		break;
    	case LU:
    		speed=new Vector2(-SPEED,SPEED);
    		break;
    	case RD:
    		speed=new Vector2(SPEED,-SPEED);
    		break;
    	case RU:
    		speed=new Vector2(SPEED,SPEED);
    		break;
		default:
			break;    	
    	}
    }
    
   
    
    public void Update (float delta){
    	pos.x +=speed.x;
    	pos.y +=speed.y;
		bounds.setPosition(pos.x, pos.y);
		setPosition(pos.x, pos.y);
		pea.act(delta);
		if (pea.getParticleEffect().isComplete()){
			pea.start();
		}
		if(pos.x>posIni.x+player.getScreen().viewport.getScreenWidth()/8 && speed.x>0){
			Player.remBull.add(this);
		}else if(pos.x<posIni.x-player.getScreen().viewport.getScreenWidth()/8 && speed.x<0){
			Player.remBull.add(this);
		}else if(pos.y>posIni.y+player.getScreen().viewport.getScreenHeight()/8 && speed.y>0){
			Player.remBull.add(this);
		}else if (pos.y<posIni.y-player.getScreen().viewport.getScreenHeight()/8 && speed.y<0){
			Player.remBull.add(this);
		}
		checkCollisions();
	}
    
    public void checkCollisions(){
		for(Array<Crect> r:player.getScreen().mapBounds){
    		for (Crect re:r){
    			if(bounds.overlaps(re)){
    				contactWorld=true;
    				pe.dispose();
    			}
    		}
    	}
    }
    
    public Texture getBullet(){
    	return bullet;
    }
}