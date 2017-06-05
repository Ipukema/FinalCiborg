package proyect.robots.screens.GameScreen;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.entities.player.Player;
import proyect.robots.screens.MyScreen;
import proyect.robots.screens.Menus.MenuScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.MapGenerator;

public class DeathScreen extends MyScreen {
	
	 public SpriteBatch batch;
	 public Stage stage;
	 public ArrayList<Button> butt;
	 public Game game;
	 public Viewport viewport;
	 public OrthographicCamera camera;
	 public TextureRegion fondo, contText;
	 public Array<TextureRegion> numbers;
	 public long start;
	 public int seconds;
	 public float mssg, startGameTimer;
	 public PlayScreen screen;
	 public MapGenerator mapper;
	
	public DeathScreen(Game game, SpriteBatch batch, PlayScreen screen) {		
		super(game);
		this.screen=screen;
		this.game=game;
		this.batch=batch;
		stage = new Stage();
		camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
		LoadResources.LoadDeathScreen();
		fondo = LoadResources.deathFondo;
		contText = LoadResources.contText;
		numbers = LoadResources.deathCount;
		mapper = new MapGenerator(game);
	}
	
	@Override
	public void show(){
		
	}
	
	
	 @Override
	    public void render (float delta) {
		 Gdx.input.setInputProcessor(stage);
		 startGameTimer += delta;
		 if(startGameTimer<1){
			 start=(long) (System.currentTimeMillis()-0.5);
		 }else{
			 if (seconds >=9){
				 Player.deathScreen=false;
				 game.setScreen(new MenuScreen(game));
				 try{
					 if(MapGenerator.stage1Song.isPlaying()){
						 MapGenerator.stage1Song.stop();
					 }else if(MapGenerator.stage2Song.isPlaying()){
						 MapGenerator.stage2Song.stop();
					 } 
				 }catch(java.lang.NullPointerException e){
					 
				 }
				 
			 }else{
				 seconds= (int) ((System.currentTimeMillis()-start)/1000);
				 mssg+=delta;
				 
				 batch.setProjectionMatrix(camera.combined);
		       
				 batch.begin();
				 batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
				 batch.draw(numbers.get(seconds), 
						 camera.viewportWidth/2-numbers.get(seconds).getRegionWidth()/2,
						 camera.viewportHeight/2-numbers.get(seconds).getRegionHeight()/2, 36,36);
				 if(mssg>0.1 && mssg<0.6){
		  			batch.draw(contText, camera.viewportWidth/2-(contText.getRegionWidth()*3)/2, 150, contText.getRegionWidth()*4, contText.getRegionHeight()*4);
				 }if(mssg>0.6){
		  			mssg=0;
		  		}    
				 batch.end();	 		  
			 }
		 }	
		 if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			 Player.deathScreen= false;
			 Player.died=false;
			 mapper.loadScreen(screen.mapName, screen.playerColor, screen.respPos.x, screen.respPos.y);
				//((MyGame)game).setScreenWithFade(new PlayScreen(game, screen.mapName, screen.playerColor, screen.respPos.x, screen.respPos.y),3);
				 //screen.beated=false;
			}	
	 }
	 
	 @Override
	    public void hide() {
	        batch.dispose();
	        stage.dispose();	
	    }
}