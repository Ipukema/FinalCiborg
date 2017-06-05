package proyect.robots.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.Menus.MenuScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.MapGenerator;
import proyect.robots.utils.loaders.SoundAssets;


public class IntroScreen extends MyScreen {

	public AsyncExecutor async = new AsyncExecutor(1);
	public AsyncResult<Void> task;
	public float startGameTimer = 0;
	public SpriteBatch batch;
	public long soundid;
    public Texture logo;
    public float mssg;
    public MapGenerator mapper;
    public Viewport viewport;
    public OrthographicCamera camera;    
    public boolean playing;
    
    public IntroScreen(Game game) {
		super(game);
		mapper = new MapGenerator(game);
	}    

    @Override
    public void show() {  
    	
    	SoundAssets.loadSongs();
    	camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
    	batch = new SpriteBatch(); 
    	logo= new Texture(Gdx.files.internal("screensAssets/splashFree.png"));
    	 //create our async task that runs our async method
        task = async.submit(new AsyncTask<Void>() {
            public Void call()  {
                asyncLoad();               
                return null;
            } 
        });  
        SoundAssets.saber1.play(); 
        SoundAssets.saber1.setVolume(0.7f*MyGame.fxValue);
        SoundAssets.saber1.setLooping(false);        
    }
    
    public void asyncLoad(){
    	LoadResources.LoadMisc1();    	
    }
   
    @Override
    public void render(float delta) {
    	Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        
        update();        
     // checks to see if the task is done.  If not draw the load screen
        if(!task.isDone()) {
        	batch.begin();
    		batch.draw(logo,0,0, camera.viewportWidth, camera.viewportHeight);
    		batch.end();    		
            return;           
        }else{          	
        	startGameTimer += delta;
        	if(startGameTimer<2){
        		batch.begin();
        		batch.draw(logo, 0, 0, camera.viewportWidth, camera.viewportHeight);
        		batch.end();  
        	}else{
        		batch.begin();
        		mssg+=delta;
         		batch.draw(LoadResources.intro, 0,0, camera.viewportWidth, camera.viewportHeight);
         		if(mssg>0.1 && mssg<0.5){
         			batch.draw(LoadResources.introText,camera.viewportWidth/2-LoadResources.introText.getRegionWidth(),100,
         					LoadResources.introText.getRegionWidth()*2,LoadResources.introText.getRegionHeight()*2); 
         		}
         		if(mssg>0.5){
         			mssg=0;
         		}    
         		batch.end();
             //stg1 : 140, 140
         		if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
         			//mapper.loadScreen("stage2", "grey", 2000, 150);
         			//mapper.loadScreen("stage1", "grey", 2600, 220);
         			((MyGame) game).setScreenWithFade(new MenuScreen(game), 3);
         		}    	
        	}          
        }       
    }    
    
    public void update(){    	
    	if((SoundAssets.saber1).isPlaying()){
        	
        }else{
        	SoundAssets.introSong.setLooping(true);
    		SoundAssets.introSong.play();
    		SoundAssets.introSong.setVolume(MyGame.musicValue);
        }
    }

    @Override
    public void hide() {
        batch.dispose();
    }    
    
    @Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}