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

public class Worm extends Png{
	public Rectangle bounds;
	public Rectangle shootBounds;
	public float GRAVITY ;
	public  float SPEED = 0.3f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting;
	 
	public Animation walk, roll, shoot;
	public ArrayList<CustomAnim> anims = new ArrayList<CustomAnim>();
	public CustomAnim currentAnim;
	
	public enum ShootingDir {L,R}
	public ShootingDir sdir;
	
	public enum WalkDir {left,right};	
	public WalkDir walkDir=WalkDir.right;
	
	public boolean shooting;
	public boolean canMove;
	public boolean hited;
	public boolean pir, pis;
	
	public String type;
	
	public static final long FIRE_RATE = 800000000L;
    public long lastShot;
    public FocusedShoot s;
	public Array<FocusedShoot> shoots = new Array<FocusedShoot>();
	Texture ball;
	
	public Animation blood;
	Texture bloodSheet;
	public Rectangle coll;
	public boolean iniciado;
	
	
	public Vector2 droll = new Vector2 (80, 120);
	
	public Worm(PlayScreen screen, float x, float y, String position) {		
		super(screen, x, y+50);
		type=position;
		if(type.equals("ground")){
			GRAVITY=-0.5f;	
			loadGroundAnims();
		}else if(type.equals("ceiling")){
			GRAVITY=+9.8f;
			loadCeilingAnims();
		}
		
		iniciado=false;
		speed = new Vector2(SPEED, GRAVITY);
		posIni=new Vector2(x,y);
		canMove=true;
		sdir=ShootingDir.L;		
		bounds = new Rectangle(posIni.x, posIni.y, 16, 25);
		shootBounds = new Rectangle(posIni.x+14, posIni.y+20, 1, 1);
		currentAnim=anims.get(2);
		bloodSheet= new Texture("Enemies/greenBlood.png");
		LoadResources.LoadBloods();
		blood=LoadResources.blueEnergy;
		TextureRegion[][] tmp = TextureRegion.split(bloodSheet, 
				bloodSheet.getWidth() / 8,
				bloodSheet.getHeight() / 8);
		coll = new Rectangle(posIni.x, posIni.y, 1,1);
		TextureRegion[] walkFrames = new TextureRegion[8 * 8];
		int index = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
	}
	public void movement(){	
		
		Player p=getScreen().getPlayer();
		float dist=Math.abs(p.getX()-getX());	
		if((getX()>=coll.x+coll.width) || (getX()<coll.x)){
			setX(posIni.x);
		}else if (dist<=60){
			shoot();
		}else if ((dist>=60) && (dist<=120)){
			canMove=true;
			runToPlayer();
		}else{
			canMove=true;
			walk();
		}							
	}
	
	@Override
	public void Update (float delta){
		checkCollisions();
		movement();		
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
	
	public void walk(){
		currentAnim=anims.get(2);
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
		setY(getY()+GRAVITY);
	}
	
	public void runToPlayer(){
		currentAnim=anims.get(0);
		if(getScreen().getPlayer().getX()<getX()){
			setX(getX()+(-SPEED));			
		}else{
			setX(getX()+(SPEED));
		}		
		setY(getY()+GRAVITY);		
	}
	
	public void shoot(){
		double angle=0;
		
		if(getScreen().getPlayer().getX()<getX()){
			currentAnim=anims.get(3);
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
			
			s= new FocusedShoot(this,"Enemies/wormShoot.png", angle, getScreen());
			shoots.add(s);
			lastShot = System.nanoTime();
		}		
	}
	
	public void checkCollisions(){
		if(type.equals("ground")){
			for (Crect r:getScreen().ground){				
	    		if (bounds.overlaps(r)){	    			
	    			//System.out.println("Toca suelo");
	    			if(!iniciado){
	    				coll=r;
	    			}	    			
	    			iniciado=true;
	    			//System.out.println(coll.x + " " +coll.y+" Worm: "+getX());
	    			contacting=true;
	    			GRAVITY=0;
	    		}
	    		else{
	    			contacting=false;
	    		}
	    	}
		}else if(type.equalsIgnoreCase("ceiling")){
			for (Crect r:getScreen().ceiling){
	    		if (bounds.overlaps(r)){  
	    			if(!iniciado){
	    				coll=r;
	    			}
	    			iniciado=true;
	    			contacting=true;  
	    			GRAVITY=0;
	    		}
	    		else{
	    			contacting=false;
	    		}
	    	}
		} 
	}
	
	public void loadGroundAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/wormAtlas.txt");
		CustomAnim an = null;
		
		for (int i=0;i<=2;i++){
    		frames.add(new TextureRegion(atlas.findRegion("wormRoll"),i*24,0,24,24));  
		}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
		for (int i=0;i<=2;i++){
    		frames.add(new TextureRegion(atlas.findRegion("wormShooting"),i*32,0,32,24)); 
    	}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();		
		for (int i=0;i<=2;i++){
    		frames.add(new TextureRegion(atlas.findRegion("wormWalk"),i*32,0,32,16));  
		}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
		for (int i=0;i<=2;i++){
			TextureRegion reg =new TextureRegion(atlas.findRegion("wormShooting"),i*32,0,32,24);
			reg.flip(true, false);
    		frames.add(reg); 
    	}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
	}
	
	public void loadCeilingAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/wormAtlas.txt");
		CustomAnim an = null;
		
		for (int i=0;i<=2;i++){
			TextureRegion reg =new TextureRegion(atlas.findRegion("wormRoll"),i*24,0,24,24);
			reg.flip(false, true);
    		frames.add(reg);  
		}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
		for (int i=0;i<=2;i++){
			TextureRegion reg=new TextureRegion(atlas.findRegion("wormShooting"),i*32,0,32,24);
			reg.flip(false, true);    		
			frames.add(reg);  
		}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
		for (int i=0;i<=2;i++){
			TextureRegion reg = new TextureRegion(atlas.findRegion("wormWalk"),i*32,0,32,16);
			reg.flip(false, true);
    		frames.add(reg);  
		}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
		for (int i=0;i<=2;i++){
			TextureRegion reg =new TextureRegion(atlas.findRegion("wormShooting"),i*32,0,32,24);
			reg.flip(true, true);
    		frames.add(reg); 
    	}
		an=new CustomAnim(0.09f,frames);
		anims.add(an);
		frames.clear();
	}
	
	public CustomAnim getCurrentAnim(){
		return currentAnim;
	}

}
