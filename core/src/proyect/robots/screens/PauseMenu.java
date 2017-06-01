package proyect.robots.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.SoundAssets;

public class PauseMenu extends MyScreen{
	
	 public SpriteBatch batch;
	 public Stage stage;
	 public ArrayList<Button> butt;
	 public TextureRegion back;
	 public Game game;
	 public BitmapFont font;
	 public Slider sl;
	 public SettingsScreen settings;
	 public static boolean set;
	 public Viewport viewport;
	 public OrthographicCamera camera;
	 
	public PauseMenu(final Game game, SpriteBatch batch) {
		super(game);
		this.game=game;	
		this.batch=batch;
		butt = new ArrayList<Button>();
		stage = new Stage();
		LoadResources.loadPauseMenu();
		
		font = new BitmapFont();		
		
		camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
		TextButton.TextButtonStyle btSt = new TextButton.TextButtonStyle();
        btSt.up = new TextureRegionDrawable(LoadResources.roff);
        btSt.down = new TextureRegionDrawable(LoadResources.ron);
        btSt.font=font;
        
	    TextButton resume = new TextButton("RESUME GAME", btSt);
	    resume.addListener(new ClickListener(){
	    	@Override
	    	public void clicked (InputEvent event, float x, float y){
	    		PlayScreen.onpause=false;
	    	}
	    });	   
	    
	    TextButton settings = new TextButton("SETTINGS", btSt);
	    settings.addListener(new ClickListener(){
	    	@Override
	    	public void clicked (InputEvent event, float x, float y){
	    		set = true ;    		
	    	}
	    });
	    
	    TextButton mainmenu = new TextButton("MAIN MENU", btSt);
	    mainmenu.addListener(new ClickListener(){
	    	@Override
	    	public void clicked (InputEvent event, float x, float y){
	    		PlayScreen.onpause=false;
	    		if(SoundAssets.stage1Song.isPlaying()){
	    			SoundAssets.stage1Song.stop();
	    			SoundAssets.stage1Song.dispose();
	    		}else if(SoundAssets.stage2Song.isPlaying()){
	    			SoundAssets.stage2Song.stop();
	    			SoundAssets.stage2Song.dispose();
	    		}
	    		SoundAssets.introSong.play();
	    		SoundAssets.introSong.setVolume(1*MyGame.musicValue);
	    		game.setScreen(new MenuScreen(game));
	    	}
	    });
	    
		TextButton exit = new TextButton("EXIT GAME", btSt);
		exit.addListener(new ClickListener(){
	    	@Override
	    	public void clicked (InputEvent event, float x, float y){
	    		System.exit(1);
	    	}
	    });
		
		butt.add(exit);
		butt.add(mainmenu);
		butt.add(settings);
		butt.add(resume);
		back = LoadResources.pauseBack;
		
		int i=0;
	    for (Button b: butt){
	    	b.setPosition(camera.viewportWidth/2-b.getWidth()/2, camera.viewportHeight/2+i-100);
	    	stage.addActor(b);
	    	i=i+85;
	    }    
	   this.settings = new SettingsScreen(game, batch);
	}
	
	@Override
	public void show(){
		if(Gdx.input.isKeyPressed(Input.Keys.P)){
			PlayScreen.onpause=true;
		}	
	}
	
	 @Override
	    public void render (float delta) {
		 
		 batch.setProjectionMatrix(camera.combined);
	       
		 Gdx.input.setInputProcessor(stage);
		 if(!set){
			 batch.begin();
			 batch.draw(back, 0, 0, camera.viewportWidth, camera.viewportHeight);
			 batch.end();
			 stage.draw();
		 }else{
			 settings.render(delta);
		 }		 		    
	 }
	 
	 @Override
	    public void hide() {
	        batch.dispose();
	        font.dispose();
	        stage.dispose();	        
	    }
}
