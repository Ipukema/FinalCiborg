package proyect.robots.entities.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import proyect.robots.entities.Png;
import proyect.robots.screens.PlayScreen;

public class Player2 extends Png{
	 public World world;
	 public Body b2body;
	 public PlayScreen screen;
	    
	public Player2(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		this.screen = screen;
        this.world = screen.getWorld();
	}

}
