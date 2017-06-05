package proyect.robots.entities.Enemies.LVL2;

import com.badlogic.gdx.graphics.Texture;
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

public class Finalboss2 extends Png{
	public Rectangle shootBounds;
	public float GRAVITY = -1f;
	public  float SPEED = 0.4f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting;
	public CustomAnim stand, shoot, die;

	
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
   	public TextureRegion blood, hit;
   	public int cont=0;
   	
	public Finalboss2(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		this.screen=screen;
		loadAnims();
		speed = new Vector2(SPEED, GRAVITY);
		posIni=new Vector2(x,y);
		sdir=ShootingDir.L;
		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);
		coll = new Rectangle(posIni.x, posIni.y, 1,1);
		currentAnim=stand;
		screen.enemies1.add(this);
		setLife(100);
	}
	
	@Override 
	public void movement(){
		Player p=getScreen().getPlayer();
		float dist=Math.abs(p.getX()-getX());
		if (dist<=100){			
			shoot();		
		}else{
			currentAnim=stand;
		}
		if(!contacting){
			setY(getY()+GRAVITY);
		}
		bounds.setPosition(new Vector2(getX(), getY()));
	}
	
	@Override
	public void Update(float delta){
		if(!died){
			movement();
			checkCollisions();
			bounds.setPosition(getX(),getY());
		}else{
			currentAnim = die;
				cont++;
				if (cont>=die.getKeyFrames().length){
					screen.score+=2000;
					 screen.beated=true;
					PlayScreen.remEnem.add(this);
					
				}
				
			}
	}
	
	@Override
	public void shoot(){
		double angle=0;
		
		if(getScreen().getPlayer().getX()<getX()){
			currentAnim=shoot;
			sdir=ShootingDir.L;
		}else{
			currentAnim=stand;
			sdir=ShootingDir.R;
		}		
		if(System.nanoTime() - lastShot >= FIRE_RATE) {
			canMove=false;
			shooting=true;
			
			double x1 = getScreen().getPlayer().getX()-shootBounds.getX();
			double y1 = getScreen().getPlayer().getY()-shootBounds.getY();
			angle = Math.atan2(y1, x1);					

			s= new FocusedShoot(this,"Enemies/s1.png", angle, getScreen());
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
    			contacting=true;
    			GRAVITY=0;
    		}
    	}
	}
	
	public void loadAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/fb2.txt");
		
		for (int i=1;i<=3;i++){
			StringBuilder sb = new StringBuilder("at");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		shoot=new CustomAnim(0.19f,frames,"shoot");
		frames.clear();
		
		blood = new TextureRegion(atlas.findRegion("blood"));
		hit = new TextureRegion(atlas.findRegion("hit"));
		
		for (int i=1;i<=9;i++){
			StringBuilder sb = new StringBuilder("die");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		die=new CustomAnim(0.000019f,frames,"die");
		frames.clear();
		
		for (int i=1;i<=4;i++){
			StringBuilder sb = new StringBuilder("stand");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		stand=new CustomAnim(0.19f,frames,"stand");
		frames.clear();
	}
}
