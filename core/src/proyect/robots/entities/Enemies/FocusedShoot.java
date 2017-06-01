package proyect.robots.entities.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.entities.player.PlayerMovementController;
import proyect.robots.screens.PlayScreen;
import proyect.robots.utils.Crect;

public class FocusedShoot extends Sprite{
	
	public Rectangle bounds;
	public static  float SPEED = 1.5f;
	public PlayerMovementController player;
	public Vector2 pos;
	public Vector2 posIni;
	public Vector2 speed;
	public boolean contactWorld;
	public boolean contactEnemy;
    
    public Texture bullet;
    public double initAngle;
    public PlayScreen screen;
    
	public FocusedShoot(Worm p, String type, double angle, PlayScreen screen) {	
		super(p);
		this.screen=screen;
		//System.out.println(angle);
		bullet = new Texture(type);
		switch (p.sdir){		
			case L:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);				
				break;
			case R:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);
				break;
			default:
				break;
		}
		pos = new Vector2(p.shootBounds.getX(), p.shootBounds.getY());
		initAngle=angle;
	}
	
	public FocusedShoot(BigRobot p, String type, double angle, PlayScreen screen) {	
		super(p);
		this.screen=screen;
		bullet = new Texture(type);
		switch (p.sdir){		
			case L:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);				
				break;
			case R:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);
				break;
			default:
				break;
		}
		pos = new Vector2(p.shootBounds.getX(), p.shootBounds.getY());
		initAngle=angle;
	}
	
	public FocusedShoot(AlienSoldier p, String type, double angle, PlayScreen screen) {	
		super(p);
		this.screen=screen;
		bullet = new Texture(type);
		switch (p.sdir){		
			case L:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);				
				break;
			case R:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);
				break;
			default:
				break;
		}
		pos = new Vector2(p.shootBounds.getX(), p.shootBounds.getY());
		initAngle=angle;
	}
	
	 public FocusedShoot(Drone1 p, Texture type, double angle, PlayScreen screen) {
		 super(p);
			this.screen=screen;
			bullet = type;
			switch (p.sdir){		
				case L:
					bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);				
					break;
				case R:
					bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);
					break;
				default:
					break;
			}
			pos = new Vector2(p.shootBounds.getX(), p.shootBounds.getY());
			initAngle=angle;
	}

	public FocusedShoot(FinalBoss1 p, Texture ball, double angle, PlayScreen screen2) {
		super(p);
		this.screen=screen2;
		bullet = ball;
		switch (p.sdir){		
			case L:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);				
				break;
			case R:
				bounds = new Rectangle(p.shootBounds.getX(), p.shootBounds.getY(), 4, 4);
				break;
			default:
				break;
		}
		pos = new Vector2(p.shootBounds.getX(), p.shootBounds.getY());
		initAngle=angle;
	}

	public void checkCollisions(){
			for(Array<Crect> r:screen.mapBounds){
	    		for (Crect re:r){
	    			if(bounds.overlaps(re)){
	    				contactWorld=true;
	    			}
	    		}
	    	}
	    }
	 
	 public void Update (float delta){
	    	setX((float) (bounds.getX()+SPEED  * Math.cos(initAngle)));
	    	setY((float) (bounds.getY()+SPEED  * Math.sin(initAngle)));
			bounds.setPosition(getX(), getY());
			checkCollisions(); 			
		}
	 
	 public Texture getBullet(){
	    	return bullet;
	    }
}
