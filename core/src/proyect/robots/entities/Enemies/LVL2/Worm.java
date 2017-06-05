package proyect.robots.entities.Enemies.LVL2;

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

public class Worm extends Png{
	public Rectangle shootBounds;
	public float GRAVITY ;
	public  float SPEED = 0.3f;
	
	public Vector2 posIni;
	public Vector2 speed;
	
	boolean contacting;
	 
	public CustomAnim walk, roll, shoot;
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
		currentAnim=walk;
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
		screen.enemies1.add(this);
		setLife(80);
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
	int cont=0;
	@Override
	public void Update (float delta){
		if(!died){
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
		}else{
			currentAnim = LoadResources.energyCollapse;
			cont++;
			if(cont>=currentAnim.getKeyFrames().length){
				PlayScreen.remEnem.add(this);
			}			
		}
		
	}
	
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
		setY(getY()+GRAVITY);
	}
	
	public void runToPlayer(){
		currentAnim=roll;
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
			currentAnim=shoot;
			sdir=ShootingDir.L;
		}else{
			currentAnim=shoot;
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
		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("b");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
		}
		roll=new CustomAnim(0.09f,frames);
		frames.clear();
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("s");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
    	}
		shoot=new CustomAnim(0.09f,frames);
		frames.clear();		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("w");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			frames.add(reg);
    	}
		walk=new CustomAnim(0.09f,frames);
		frames.clear();		
	}
	
	public void loadCeilingAnims(){
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Enemies/wormAtlas.txt");
		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("b");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			reg.flip(false, true);
			frames.add(reg);
		}
		roll=new CustomAnim(0.09f,frames);
		
		frames.clear();
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("s");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			reg.flip(false, true);
			frames.add(reg);
    	}
		shoot=new CustomAnim(0.09f,frames);
		frames.clear();		
		for (int i=1;i<=2;i++){
			StringBuilder sb = new StringBuilder("w");
			TextureRegion reg =new TextureRegion(atlas.findRegion(sb.append(i).toString()));
			reg.flip(false, true);
			frames.add(reg);
    	}
		walk=new CustomAnim(0.09f,frames);
		frames.clear();		
	}
	
	public CustomAnim getCurrentAnim(){
		return currentAnim;
	}

}
