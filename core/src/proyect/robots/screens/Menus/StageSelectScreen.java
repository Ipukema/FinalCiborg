
package proyect.robots.screens.Menus;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.MyScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.MapGenerator;


public class StageSelectScreen extends MyScreen {

    float startGameTimer = 0;
    BitmapFont font;
    SpriteBatch batch;
    public Game game;
    public TextureRegion st1;
    public TextureRegion st2;
    public TextureRegion st3;
    public TextButton btst1;
    public TextButton btst2;
    public TextButton btst3;
    public ArrayList<Button> botones;
    public Stage stage;
    public TextureRegion fondo;  
    public MapGenerator mapper;
    public Viewport viewport;
    private OrthographicCamera camera;

    public StageSelectScreen(Game game) {    	
        super(game);
        this.game=game;  
        mapper = new MapGenerator(this.game);
        
    }    
    
    @Override
    public void show() {
    	camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
        
        st1 = LoadResources.st1;
        st2 = LoadResources.st2;
        st3 = LoadResources.st3;
        TextButton.TextButtonStyle btSt = new TextButton.TextButtonStyle();
        btSt.up = new TextureRegionDrawable(new TextureRegion(st1));
        btSt.font=font;
        btst1 = new TextButton ("STAGE 1",btSt);
        
        TextButton.TextButtonStyle btSt2 = new TextButton.TextButtonStyle();
        btSt2.up = new TextureRegionDrawable(new TextureRegion(st2));
        btSt2.font=font;
        btst2 = new TextButton ("STAGE 2",btSt2);
        
        TextButton.TextButtonStyle btSt3 = new TextButton.TextButtonStyle();
        btSt3.up = new TextureRegionDrawable(new TextureRegion(st3));
        btSt3.font=font;
        btst3 = new TextButton ("STAGE 3",btSt3);
        
        botones = new ArrayList<Button>();
        stage = new Stage();
        fondo = LoadResources.stageSelectBack;
        Gdx.input.setInputProcessor(stage);
        
        botones.add(btst1);
        botones.add(btst2);
        botones.add(btst3);       
        
        btst3.setPosition(camera.viewportWidth-btst3.getWidth()-100, 200);
        stage.addActor(btst3);
        btst2.setPosition(camera.viewportWidth/2-btst2.getWidth()/2, 400);
        stage.addActor(btst2);
        btst1.setPosition(btst1.getWidth(), 300);
        stage.addActor(btst1);
        
        btst3.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		System.out.println("Proximamente.........");
        	}
        });
               
        btst2.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		newGame("stage2");
        	}
        });
        
        btst1.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		newGame("stage1");
        	}			
        });
    }

    @Override
    public void render(float delta) {
        startGameTimer += delta;
        
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);    
        batch.setProjectionMatrix(camera.combined);
        
       
        stage.getBatch().begin();
        stage.getBatch().draw(fondo, 0, 0,  camera.viewportWidth, camera.viewportHeight); 
        stage.getBatch().end();
        stage.draw();
    }
    
    private void newGame(String map) {
    	((MyGame) game).setScreenWithFade(new CharSelector(game, map),3);
	}
    
    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
    }    
    
    @Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}