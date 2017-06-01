package proyect.robots.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import proyect.robots.screens.PlayScreen;
import proyect.robots.utils.CustomAnim;


public abstract class Png extends Sprite{
	
	enum State {RUNNING, SHOOTING, STAND, JUMP, DEAD};
	private boolean running, shooting, standing, jumping, dead;
	private State currentState;
	private State previousState;   
    
	private World world;
    private PlayScreen screen;
    private Body b2body;
    private TextureAtlas atlas;  
    public CustomAnim currentAnim;
	public Rectangle bounds;
	public int life;
	public boolean died;
	
	public Png (PlayScreen screen, float x, float y){
    	this.screen=screen;
    	this.world=screen.getWorld();
    	this.setX(x);
    	this.setY(y);    	
    }
    
    public boolean isDied() {
		return died;
	}

	public void setDied(boolean died) {
		this.died = died;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getLife() {
		return life;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public CustomAnim getCurrentAnim() {
		return currentAnim;
	}

	public void setCurrentAnim(CustomAnim currentAnim) {
		this.currentAnim = currentAnim;
	}


    public void Update(float dt){
		this.setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
	}
    
   public void getHit(int dmg){
	   setLife(life-dmg);
		if(life <=0){
			died=true;
		}
   }
   
    public void movement(){	
    	
    }
    
    public void walk(){
    	
    }
    
    public void runToPlayer(){
    	
    }
    
    public void shoot(){
    	
    }
    
    public void checkCollisions(){
    	
    }
    
    public State getPreviousState() {
		return previousState;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}
	
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Body getB2body() {
		return b2body;
	}

	public void setB2body(Body b2body) {
		this.b2body = b2body;
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	public void setAtlas(TextureAtlas atlas) {
		this.atlas = atlas;
	}	

	public PlayScreen getScreen() {
		return screen;
	}

	public void setScreen(PlayScreen screen) {
		this.screen = screen;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public boolean isStanding() {
		return standing;
	}

	public void setStanding(boolean standing) {
		this.standing = standing;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
}
