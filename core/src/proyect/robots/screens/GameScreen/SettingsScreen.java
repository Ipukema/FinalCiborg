package proyect.robots.screens.GameScreen;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.MyScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.SoundAssets;

public class SettingsScreen extends MyScreen{
	
	 public SpriteBatch batch;
	 public Stage stage;
	 public ArrayList<Button> butt;
	 public TextureRegion back, logo;
	 public Game game;
	 public BitmapFont font;
	 public Slider slMusic, slFx;
	 public ImageButton backButt;
	 public Viewport viewport;
	 private OrthographicCamera camera;	 
	 
	public SettingsScreen(Game game, SpriteBatch batch) {
		super(game);
		this.game=game;	
		this.batch=batch;
		LoadResources.loadSettingsScreen();
		stage = new Stage();
	
		camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
		logo = LoadResources.logo;
		
		int i=0;
		i=i+85;
		
		TextureRegionDrawable bar = new TextureRegionDrawable(LoadResources.bar);
		TextureRegionDrawable note = new TextureRegionDrawable(LoadResources.note);
		TextureRegionDrawable fx = new TextureRegionDrawable(LoadResources.fx);	
		back = LoadResources.setBack;
		
		SliderStyle st1 = new SliderStyle(bar, note);
		SliderStyle st = new SliderStyle(bar, fx);
		
		slMusic = new Slider(0, 1, 0.01f, false, st1);
		slMusic.setValue(1);
		stage.addActor(slMusic);
		slMusic.setPosition(camera.viewportWidth/2-slMusic.getWidth()/2, 450);
		i=i+85;
		
		slFx = new Slider(0, 1, 0.01f, false, st);
		slFx.setValue(1);
		stage.addActor(slFx);	
		slFx.setPosition(camera.viewportWidth/2-slFx.getWidth()/2, 300);
		i=i+85;
		
		Drawable drawable = new TextureRegionDrawable(new TextureRegion(LoadResources.backButton));
        backButt = new ImageButton(drawable);
        backButt.setSize(120, 120);
        backButt.addListener(new ClickListener(){
        	@Override
	    	public void clicked (InputEvent event, float x, float y){
	    		PauseMenu.set=false;
	    	}
        });
        backButt.setPosition(camera.viewportWidth/2-backButt.getWidth()/2, 100);
        stage.addActor(backButt);
        i=0;
	}
	
	@Override
	public void show(){
		
	}
	
	 @Override
	    public void render (float delta) {
		 	Gdx.input.setInputProcessor(stage);				
			batch.begin();
			batch.draw(logo,0,0, camera.viewportWidth,camera.viewportHeight);
			batch.end();
		    stage.draw();	
		    MyGame.musicValue = slMusic.getValue();
		    MyGame.fxValue = slFx.getValue();
		    if(SoundAssets.stage1Song.isPlaying()){
		    	SoundAssets.stage1Song.setVolume(1*MyGame.musicValue);
		    }else if (SoundAssets.stage2Song.isPlaying()){
		    	SoundAssets.stage2Song.setVolume(1*MyGame.musicValue);
		    }		    
	 }
	 
	 @Override
	    public void hide() {
	        batch.dispose();
	        stage.dispose();
	    }
	 
	  @Override
		public void resize(int width, int height) {
			viewport.update(width, height);
		}
}
