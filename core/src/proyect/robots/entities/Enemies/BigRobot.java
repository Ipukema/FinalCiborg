package proyect.robots.entities.Enemies;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import proyect.robots.entities.Png;
import proyect.robots.entities.player.Player;
import proyect.robots.screens.PlayScreen;
import proyect.robots.utils.Crect;
import proyect.robots.utils.CustomAnim;
import proyect.robots.utils.loaders.LoadResources;

public class BigRobot extends Png{
	
	public Rectangle shootBounds;
	public float GRAVITY = -1f;
	public  float SPEED = 0.4f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting;
	public CustomAnim walk, shoot, die;
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
   	Texture ball;
   	public Rectangle coll;
   	public PlayScreen screen;
    
	public BigRobot(PlayScreen screen, float x, float y) {
		super(screen, x, y);	
		loadAnims();
		speed = new Vector2(SPEED, GRAVITY);
		posIni=new Vector2(x,y);
		sdir=ShootingDir.L;
		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);
		coll = new Rectangle(posIni.x, posIni.y, 1,1);
		currentAnim=walk;
		screen.enemies1.add(this);
		setLife(100);
	}
	
	@Override
	public void movement(){	
		Player p=getScreen().getPlayer();
		float dist=Math.abs(p.getX()-getX());
		if (dist<=60){			
			shoot();
		}else if ((dist>60) && (dist<140)){
			runToPlayer();
		}else{
			canMove=true;
			walk();
		}
		if(!contacting){
			setY(getY()+GRAVITY);
		}
		if((getX()+bounds.width/2>=coll.x+coll.width) || (getX()-bounds.width/2<coll.x)){
			setX(posIni.x);
		}
		bounds.setPosition(new Vector2(getX(), getY()));		
	}

	 
	@Override
	public void walk(){
		
		currentAnim=walk;
		if (walkDir==WalkDir.left){
			setX(getX()-SPEED);		
			if(getX()<posIni.x-50){
				walkDir=WalkDir.right;
			}
		}else{
			setX(getX()+SPEED);
			if(getX()>posIni.x+50){
				walkDir=WalkDir.left;
			}
		}
		bounds.setPosition(new Vector2(getX(), getY()));	
	}
	
	int cont=0;
	@Override
	public void Update (float delta){		
		if(!died){			
			movement();		
			checkCollisions();	
			bounds.setPosition(getX(),getY());
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
			currentAnim = LoadResources.energyCollapse;
			cont++;			
			if(cont>=currentAnim.getKeyFrames().length){
				PlayScreen.remEnem.add(this);				
			}			
		}
		bounds.setPosition(new Vector2(getX(), getY()));	
	}
	
	@Override
	public void runToPlayer(){
		currentAnim=walk;
		if(getScreen().getPlayer().getX()<getX()){
			sdir=ShootingDir.L;
			setX(getX()+(-SPEED));			
		}else{
			setX(getX()+(SPEED));
			sdir=ShootingDir.R;
		}
		bounds.setPosition(new Vector2(getX(), getY()));	
	}
	
	@Override
	public void shoot(){
		double angle=0;
		
		if(getScreen().getPlayer().getX()<getX()){
			currentAnim=anims.get(1);
			sdir=ShootingDir.L;
		}else{
			currentAnim=anims.get(1);
			sdir=ShootingDir.R;
		}		
		if(System.nanoTime() - lastShot >= FIRE_RATE) {
			canMove=false;
			shooting=true;
			
			double x1 = getScreen().getPlayer().getX()-shootBounds.getX();
			double y1 = getScreen().getPlayer().getY()-shootBounds.getY();
			angle = Math.atan2(y1, x1);					

			s= new FocusedShoot(this,"Enemies/robotShoot.png", angle, getScreen());
			shoots.add(s);
			lastShot = System.nanoTime();
		}else{
			canMove=true;
		}
	}
	
	@Override
	public void checkCollisions(){		
		for (Crect r:getScreen().ground){				
    		if (bounds.overlaps(r)){ 
    			coll=r;
    			contacting=true;
    			GRAVITY=0;
    		}
    	}
	}
	
	public void loadAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/BigRobot.txt");		
		
		for (int i=1;i<=10;i++){
			StringBuilder sb = new StringBuilder("w");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		walk=new CustomAnim(0.09f,frames,"walk");
		anims.add(walk);		
		frames.clear();
		
		for (int i=1;i<=6;i++){
			StringBuilder sb = new StringBuilder("a");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		shoot=new CustomAnim(0.12f,frames,"shoot");
		anims.add(shoot);		
		frames.clear();
		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("die");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		die=new CustomAnim(0.09f,frames,"die");
		anims.add(die);		
		frames.clear();
	}
}

