package proyect.robots;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import proyect.robots.screens.IntroScreen;
import proyect.robots.screens.MyScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.saveGame.PlayerSaverArray;
import proyect.robots.utils.saveGame.ScoreManager;

public class MyGame extends Game {
	public static int LVL;
	public static float musicValue, fxValue;
	public static int GW = 1200, GH=720;
	public static PlayerSaverArray saver = new PlayerSaverArray();
	
	private Actor fadeActor = new Actor();
    private ShapeRenderer fadeRenderer;
	
	@Override
	public void create () {
		musicValue =1;
		fxValue=1;
		ScoreManager.openFile();
		LoadResources.LoadMisc1();
		 fadeRenderer = new ShapeRenderer(8);
		 setScreenWithFade(new IntroScreen(this), 1);
	}
	 public void setScreenWithFade (final MyScreen screen, float duration) {
	        fadeActor.clearActions();
	        fadeActor.setColor(Color.CLEAR);
	        fadeActor.addAction(Actions.sequence(
	            Actions.color(Color.BLACK, duration/2f, Interpolation.fade),
	            Actions.run(new Runnable(){public void run(){setScreen(screen);}}),
	            Actions.color(Color.CLEAR, duration/2f, Interpolation.fade)
	        ));
	    }
	 
	 @Override
	    public void render (){
	        super.render();

	        fadeActor.act(Gdx.graphics.getDeltaTime());
	        float alpha = fadeActor.getColor().a;
	        if (alpha != 0){
	            fadeRenderer.begin(ShapeType.Filled);
	            fadeRenderer.setColor(0, 0, 0, alpha);
	            fadeRenderer.rect(-1, -1, 2, 2); //full screen rect w/ identity matrix
	            fadeRenderer.end();
	        }
	    }
	
}/* private Actor fadeActor = new Actor();
    private ShapeRenderer fadeRenderer;

    public void create (){ 
        //...your create() code

        fadeRenderer = new ShapeRenderer(8);
    }

    public void setScreenWithFade (final Screen screen, float duration) {
        fadeActor.clearActions();
        fadeActor.setColor(Color.CLEAR);
        fadeActor.addAction(Actions.sequence(
            Actions.color(Color.BLACK, duration/2f, Interpolation.fade),
            Actions.run(new Runnable(){public void run(){setScreen(screen);}}),
            Actions.color(Color.CLEAR, duration/2f, Interpolation.fade)
        ));
    }

    @Override
    public void render (){
        super.render();

        fadeActor.act(Gdx.graphics.getDeltaTime());
        float alpha = fadeActor.getColor().a;
        if (alpha != 0){
            fadeRenderer.begin(ShapeType.Filled);
            fadeRenderer.setColor(0, 0, 0, alpha);
            fadeRenderer.rect(-1, -1, 2, 2); //full screen rect w/ identity matrix
            fadeRenderer.end();
        }
    }

}*/