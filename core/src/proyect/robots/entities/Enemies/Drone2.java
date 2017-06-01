package proyect.robots.entities.Enemies;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.screens.PlayScreen;
import proyect.robots.utils.CustomAnim;

public class Drone2 extends Drone1{
	
	

	public Rectangle shootBounds;
	public float GRAVITY = 0f;
	public  float SPEED = 0.4f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting, down;
	public int downCounter;
	public CustomAnim att1, shoot, move;
	public ArrayList<CustomAnim> anims = new ArrayList<CustomAnim>();
	
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
    public FocusedShoot s;
   	public Array<FocusedShoot> shoots = new Array<FocusedShoot>();
   	public Texture ball;
   	public Rectangle coll;
   	
   	public Drone2(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		currentAnim=move;
		posIni= new Vector2(x, y);
		sdir=ShootingDir.L;
		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);
		coll = new Rectangle(posIni.x, posIni.y, 1,1);
		currentAnim=move;
		down=true;
		downCounter=0;
		screen.enemies1.add(this);
	}
		
	@Override 
	public void loadAnims(){
		ball= new Texture(Gdx.files.internal("Enemies/robotShoot.png"));
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/Drone2.txt");	
		
		for (int i=1;i<=4;i++){
			StringBuilder sb = new StringBuilder("s");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		move=new CustomAnim(0.009f,frames,"move");	
		frames.clear();
	}
	
	@Override
	public void walk(){
		
		currentAnim=move;
		if (walkDir==WalkDir.left){
			setX(getX()-SPEED);		
			if(getX()<posIni.x-40){
				walkDir=WalkDir.right;
			}
		}else{
			setX(getX()+SPEED);
			//System.out.println("posicion incial: "+posIni.x);
			if(getX()>posIni.x+50){
				walkDir=WalkDir.left;
			}
		}
		if(down){
			setY(getY()-1);
			downCounter++;
			if(downCounter>=5){
				down=false;
				downCounter=0;
			}
		}else{
			setY(getY()+1);
			downCounter++;
			if(downCounter>=5){
				down=true;
				downCounter=0;
			}
		}
	}
	
	@Override
	public void Update (float delta){
		movement();		
		checkCollisions();	
		bounds.setPosition(this.getX(), this.getY());
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
	}
	@Override 
	public CustomAnim getCurrentAnim(){
		return move;
		
	}
}
