package proyect.robots.utils;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {
	
	   public ParticleEffect particleEffect;
	   public Vector2 acc = new Vector2();
	   public Sprite sp;
	   public Vector2 pos;
	   
	   public ParticleEffectActor(ParticleEffect particleEffect, Sprite ball, Vector2 pos) {
	      super();
	      this.pos=pos;
	      this.particleEffect = particleEffect;
	      this.sp=ball;
	      setPosition(ball.getX(), ball.getY());
	   }
	  
	public ParticleEffect getParticleEffect() {
		return particleEffect;
	}

	public Sprite getSp() {
		return sp;
	}

	public void setParticleEffect(ParticleEffect particleEffect) {
		this.particleEffect = particleEffect;
	}

	public void setSp(Sprite sp) {
		this.sp = sp;
	}

	public void draw(SpriteBatch batch, float delta) {
	      particleEffect.draw(batch);
	   }

	   @Override
	   public void act(float delta) {
	      super.act(delta);
	      acc.set(getWidth()/2, getHeight()/2);
	      localToStageCoordinates(acc);
	      particleEffect.setPosition(sp.getX()+pos.x, sp.getY()+pos.y);   
	      particleEffect.update(delta);
	   }


	   public void start() {
	      particleEffect.start();
	   }

	   public void allowCompletion() {
	      particleEffect.allowCompletion();
	   }
	   
	}