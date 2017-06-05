package proyect.robots.entities;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Drop extends Sprite{
	
	public Texture icon;
	public Random rand;
	public Vector2 pos;
	public float delta;
	public final float delete = 6;
	public String dropType;
	
	public Rectangle bounds;
	
	

	public Drop(ArrayList<Drop> crafting, Vector2 vec, float delta){
		crafting.add(this);
		rand=new Random();
		int n = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		if (n<50){
			crafting.remove(this);
		}else if (n>=50 && n<70){					
			icon = new Texture(Gdx.files.internal("bolt-shield.png"));
			dropType="shield";
		}else if (n>=70 && n<90){
			icon = new Texture(Gdx.files.internal("rocket.png"));
			dropType="rocket";
		}else if (n>=90){
			icon = new Texture(Gdx.files.internal("heart-wings.png"));
			dropType="life";
		}
		pos = vec;
		setPosition(pos.x, pos.y);
		bounds= new Rectangle(pos.x, pos.y, 6, 6);
		this.delta=0;
	}
	
	public void update(float delta){
		this.delta+=delta;
	}
	
	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}
	
	public float getDelete() {
		return delete;
	}

	public String getDropType() {
		return dropType;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setDropType(String dropType) {
		this.dropType = dropType;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public Texture getIcon() {
		return icon;
	}

	public Random getRand() {
		return rand;
	}

	public Vector2 getPos() {
		return pos;
	}

	public void setIcon(Texture icon) {
		this.icon = icon;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}
