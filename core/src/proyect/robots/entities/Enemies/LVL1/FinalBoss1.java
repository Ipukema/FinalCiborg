package proyect.robots.entities.Enemies.LVL1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.entities.Png;
import proyect.robots.entities.Enemies.FocusedShoot;
import proyect.robots.entities.player.Player;
import proyect.robots.screens.GameScreen.PlayScreen;
import proyect.robots.utils.Crect;
import proyect.robots.utils.CustomAnim;
import proyect.robots.utils.loaders.LoadResources;

public class FinalBoss1 extends Png{
	
	public Rectangle shootBounds;
	public float GRAVITY = 0f;
	public  float SPEED = 0.2f;
	
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
	
	public static final long FIRE_RATE = 1200000000L;
    public long lastShot;   
    public FocusedShoot s;
   	public Array<FocusedShoot> shoots = new Array<FocusedShoot>();
   	public Texture ball;
   	public Rectangle coll;
   	public PlayScreen screen;
   	
	public FinalBoss1(PlayScreen screen, float x, float y) {
		
		super(screen, x, y);
		this.screen=screen;
		loadAnims();
		speed = new Vector2(SPEED, GRAVITY);
		posIni=new Vector2(x,y);
		sdir=ShootingDir.L;		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);
		coll = new Rectangle(posIni.x, posIni.y, 1,1);
		currentAnim=att1;
		down=true;
		downCounter=0;
		this.screen.enemies1.add(this);
		ball= new Texture(Gdx.files.internal("Enemies/fb1Bullet.png"));
		setLife(250);
		setDied(false);
		screen.enemies1.add(this);
	}
	
	@Override
	public void shoot(){
		double angle=0;
		
		if(getScreen().getPlayer().getX()<getX()){
			currentAnim=att1;
			sdir=ShootingDir.L;
		}else{
			currentAnim=att1;
			sdir=ShootingDir.R;
		}		
		if(System.nanoTime() - lastShot >= FIRE_RATE) {
			canMove=false;
			shooting=true;
			
			double x1 = getScreen().getPlayer().getX()-shootBounds.getX();
			double y1 = getScreen().getPlayer().getY()-shootBounds.getY();
			angle = Math.atan2(y1, x1);					

			s= new FocusedShoot(this, ball, angle, getScreen());
			shoots.add(s);
			lastShot = System.nanoTime();
		}		
	}
	@Override
	public void movement(){	
		Player p=getScreen().getPlayer();
		float dist=Math.abs(p.getX()-getX());
		if (dist<=80){			
			shoot();
		}else{
			canMove=true;
			walk();
		}
		bounds.setPosition(new Vector2(getX(), getY()));		
	}	
	
	@Override
	public void walk(){	
		currentAnim=att1;
		if (walkDir==WalkDir.left){
			setX(getX()-SPEED);		
			if(getX()<posIni.x-60){
				walkDir=WalkDir.right;
			}
		}else{
			setX(getX()+SPEED);
			if(getX()>posIni.x+60){
				walkDir=WalkDir.left;
			}
		}
		if(down){
			setY(getY()-0.5f);
			downCounter++;
			if(downCounter>=5){
				down=false;
				downCounter=0;
			}
		}else{
			setY(getY()+0.5f);
			downCounter++;
			if(downCounter>=5){
				down=true;
				downCounter=0;
			}
		}
	}
	
	int cont=0;
	@Override
	public void Update (float delta){
		if(!isDied()){			
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
		}else{
			currentAnim = LoadResources.fireSmoke;
			cont++;
			if(cont>=currentAnim.getKeyFrames().length){
				PlayScreen.remEnem.add(this);
			}
		}
	}	
	
	@Override
	public void checkCollisions(){		
		for (Crect r:getScreen().left){				
    		if (bounds.overlaps(r)){ 
    			walkDir= WalkDir.right;
    		}
    	}
		for (Crect r:getScreen().right){
			if (bounds.overlaps(r)){ 
    			walkDir= WalkDir.left;
    		}
		}
	}
	
	public void loadAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/FinalBoss1.txt");	
		
		for (int i=1;i<=3;i++){
			StringBuilder sb = new StringBuilder("fb");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		att1=new CustomAnim(0.2f,frames,"att1");
		anims.add(att1);		
		frames.clear();		
	}
}
