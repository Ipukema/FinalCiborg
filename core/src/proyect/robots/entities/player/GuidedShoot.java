package proyect.robots.entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.entities.Png;
import proyect.robots.utils.Crect;
import proyect.robots.utils.ParticleEffectActor;

public class GuidedShoot extends Shoot{
	public Rectangle bounds;
	public static  float SPEED = 0.5f;
	public Player player;
	public Vector2 pos;
	public Vector2 posIni;
	public Vector2 speed;
	public boolean contactWorld;
	public boolean contactEnemy;
	
	public TextureRegion bullet;
    public ParticleEffect pe;
    
    public ParticleEffectActor pea ;
    
    public Png target;
    public Sprite img;
    public double distance;
    public double angle;
    
    public GuidedShoot (Player player, String type, String particle){
    	super(player, type, particle);
    	this.player=player;
    	TextureAtlas atlas = new TextureAtlas("misile.txt");
    	bullet = new TextureRegion(atlas.findRegion("misile"));
    	img= new Sprite(bullet);
    	target=getTarget();
    	angle=getInitAngle();
    	bounds = new Rectangle(player.pos.x, player.pos.y,bullet.getRegionWidth()/3, bullet.getRegionHeight()/3);
    	
    	pos = new Vector2(player.bounds.getX()+4, player.bounds.getY()+9);
    	posIni=pos;
    	pe = new ParticleEffect();
		pe.load(Gdx.files.internal("Particles/"+particle),Gdx.files.internal(""));
		changeParticleAngle(0);
		img.setOrigin(getWidth()/2, getHeight()/2);
		img.rotate((float)getInitAngle());
		
    }

	public void changeParticleAngle(float targetAngle) {
		Array<ParticleEmitter> emitters = pe.getEmitters();
		
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
    
    public Png getTarget(){
    	double dist= 100000000;
    	Png target = null;
    	for (Png e:player.getScreen().enemies1){
    		distance = Math.hypot(player.getX()-e.getX(), player.getY()-e.getY());
    		if(distance<dist){
    			dist=distance;
    			target = e;
    		}    		
    	}
		return target;
    }
    
    public double getInitAngle(){
    	double x1 = target.getX()+target.bounds.width/2-player.getX();
		double y1 = target.getY()+target.bounds.height/2-player.getY();		
		return angle = Math.atan2(y1, x1);			
    }
    
    public double correctAngle(){
    	double x1 = target.getX()+target.bounds.width/2-getX();
		double y1 = target.getY()+target.bounds.height/2-getY();
		
		angle = Math.atan2(y1, x1);	
		
		return angle;
		
    }    
   
    public void Update (float delta){
    	if(target.isDied()){
    		target=getTarget();
		}else{
			setX((float) (bounds.getX()+SPEED  * Math.cos(correctAngle())));
	    	setY((float) (bounds.getY()+SPEED  * Math.sin(correctAngle())));
			bounds.setPosition(getX(), getY());
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
}