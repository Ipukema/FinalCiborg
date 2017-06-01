package proyect.robots.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.utils.loaders.LoadResources;

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
	
	public DeathScreen(Game game, SpriteBatch batch) {
		super(game);
		this.game=game;
		this.batch=batch;
		
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
	}
	
	@Override
	public void show(){
		if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
			//continueGame ----> en el futuro..........
			/*hay que marcar los puntos de respawn en el mapa, para guardar posiciones validas
			 * eso es con el tiledMap creando una capa de objetos, y un variable Vector2 en la
			 * PlayScreen para recogerla. Cuando el bounds del player toque esos puntos, se 
			 * guardara la posicion y solo se tendra que volver a instanciar un nuevo player en la 
			 * pantalla pasandole dichos valores de posicion guardados en el vector2*/
		}	
	}
	
	
	 @Override
	    public void render (float delta) {
		 startGameTimer += delta;
		 if(startGameTimer<1){
			 start=(long) (System.currentTimeMillis()-0.5);
		 }else{
			 if (seconds >=9){
				 game.setScreen(new MenuScreen(game));
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
	 }
	 
	 @Override
	    public void hide() {
	        batch.dispose();
	        stage.dispose();	
	    }
}