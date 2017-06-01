package proyect.robots.entities.Enemies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.entities.Png;
import proyect.robots.entities.player.Player;
import proyect.robots.screens.PlayScreen;
import proyect.robots.utils.CustomAnim;

public class AlienSoldier extends Png {
	
	public Rectangle bounds;
	public Rectangle shootBounds;
	public float GRAVITY = -9.8f;
	public  float SPEED = 0.3f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting;
	
	public CustomAnim appear, runR, transformR, attackR, trunR, runL, transformL, attackL, trunL;
	public ArrayList<CustomAnim> anims = new ArrayList<CustomAnim>();
	public CustomAnim currentAnim;
	public Animation blood;
	
	public enum ShootingDir {L,R}
	public ShootingDir sdir;
	
	public enum WalkDir {left,right};	
	public WalkDir walkDir=WalkDir.right;
	
	public boolean shooting;
	public boolean canMove;
	public boolean hited;
	
	public static final long FIRE_RATE = 800000000L;
    public long lastShot;
    public  boolean appeared=false;
  	public boolean active = false;
  	public boolean transformed=false;
  	public boolean running = false;
   
  	
    public FocusedShoot s;
   	public Array<FocusedShoot> shoots = new Array<FocusedShoot>();
    
	public AlienSoldier(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		loadAnims();
		loadPlayerEffects();
		speed = new Vector2(SPEED, GRAVITY);
		posIni=new Vector2(x,y);
		canMove=true;
		sdir=ShootingDir.L;
		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);		
		currentAnim=appear;
	}
	
	public void movement(float delta){	
		Player p=getScreen().getPlayer();
		float dist=Math.abs(p.getX()-getX());
		if(dist<=140){
			appeared=true;		
			active=true;
		}
		if(active && appeared){
			if ((dist>80) && (dist<140)){
				if(getScreen().getPlayer().getX()<getX()){
					setX(getX()+(-SPEED));
					currentAnim=runL;
				}else{
					setX(getX()+(SPEED));
					currentAnim=runR;
				}		
				setY(getY()+GRAVITY);	
			}else if (dist<=80 && dist>=60){
				if(p.getX()<getX()){
					currentAnim= transformL;
				}else{
					currentAnim= transformR;
				}				
			}else if (dist<=60){
				//System.out.println("dispara");				
				shoot();
			}
		}
		switch(sdir){
		case L:
			shootBounds.setPosition(this.getX()+1, this.getY()+15);
			break;
		case R:
			shootBounds.setPosition(this.getX()+15, this.getY()+15);
			break;
		default:
			break;							
	}	
		bounds.setPosition(this.getX(), this.getY());
	}
	
	@Override
	public void Update (float delta){
		checkCollisions();
		movement(delta);			
	}

	
	public void shoot(){
		double angle=0;
		
		if(getScreen().getPlayer().getX()<getX()){
			currentAnim=attackL;
			sdir=ShootingDir.L;
		}else{
			currentAnim=attackR;
			sdir=ShootingDir.R;
		}		
		if(System.nanoTime() - lastShot >= FIRE_RATE) {
			canMove=false;
			shooting=true;
			
			double x1 = getScreen().getPlayer().getX()-shootBounds.getX();
			double y1 = getScreen().getPlayer().getY()+10-shootBounds.getY();
			angle = Math.atan2(y1, x1);			
			
			s= new FocusedShoot(this,"Enemies/robotShoot.png", angle, getScreen());
			shoots.add(s);
			lastShot = System.nanoTime();
		}		
	}
	
	public void checkCollisions(){		
		for (Rectangle r:getScreen().ground){				
    		if (bounds.overlaps(r)){  
    			contacting=true;	    			
    			GRAVITY=0;
    		}
    		else{
    			contacting=false;
    		}
    	}
	}
	
	public void loadPlayerEffects(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/alienAppearEffect.txt");
		
		for (int i=0;i<=7;i++){	
			StringBuilder sb = new StringBuilder("0");
    		frames.add(atlas.findRegion(sb.append(i).toString()));    		    		
    	}		
		appear=new CustomAnim(0.09f,frames,"appear");
		anims.add(appear);
		frames.clear();		
	}
	
	public void loadAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		Array<TextureRegion> fframes = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/alienSoldier.txt");		
		
		for (int i=1;i<=3;i++){
			StringBuilder sb = new StringBuilder("a");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg); 
			reg.flip(true, false);
			fframes.add(reg);
		}
		trunR=new CustomAnim(0.09f,frames,"transRunR");
		anims.add(trunR);
		trunL= new CustomAnim(0.09f,fframes,"transRunL");
		anims.add(trunL);
		frames.clear();
		fframes.clear();
		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("a");
			
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
			reg.flip(true, false);
			fframes.add(reg);
		}
		attackR= new CustomAnim(0.09f,frames,"attackR");
		anims.add(attackR);
		attackL=new CustomAnim(0.09f,fframes,"attackL");
		anims.add(attackL);
		frames.clear();
		fframes.clear();
		
		for (int i=1;i<=4;i++){
			StringBuilder sb = new StringBuilder("r");
			
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg); 
			reg.flip(true, false);
			fframes.add(reg);
		}
		runR = new CustomAnim(0.09f,frames,"runR");
		anims.add(runR);
		runL = new CustomAnim(0.09f,fframes,"runL");
		anims.add(runL);
		frames.clear();
		fframes.clear();
		
		for (int i=1; i<=7;i++){
			StringBuilder sb = new StringBuilder("t");
			System.out.println(sb.toString()+i);
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg); 
			reg.flip(true, false);
			fframes.add(reg);
		}
		transformR = new CustomAnim(0.09f,frames,"transformR");
		anims.add(transformR);
		transformL = new CustomAnim(0.09f,fframes,"transformL");
		anims.add(transformL);
		frames.clear();
		fframes.clear();
	}

	public CustomAnim getCurrentAnim(){
		return currentAnim;
	}

}
