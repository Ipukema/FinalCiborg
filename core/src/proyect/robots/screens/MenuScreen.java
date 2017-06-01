package proyect.robots.screens;

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
import proyect.robots.utils.loaders.LoadResources;


public class MenuScreen extends MyScreen {

    float startGameTimer = 0;
    BitmapFont font;
    SpriteBatch batch;
    public Game game;
    public TextureRegion button;
    public TextButton start;
    public TextButton exit;
    public TextButton rankings;
    public ArrayList<Button> botones;
    public Stage stage;
    public TextureRegion fondo;   
    public Viewport viewport;
    public OrthographicCamera camera;

    public MenuScreen(Game game) {    	
        super(game);
        this.game=game;        
    }
    
    private void newGame() {
    	game.setScreen(new Menu2Screen (game));		
	}
    
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        button = LoadResources.button;
        TextButton.TextButtonStyle btSt = new TextButton.TextButtonStyle();
        btSt.up = new TextureRegionDrawable(new TextureRegion(button));
        btSt.font=font;
        start = new TextButton ("START",btSt);
        exit = new TextButton ("EXIT",btSt);
        rankings = new TextButton ("RANKINGS",btSt);
        botones = new ArrayList<Button>();
        stage = new Stage();
        fondo = LoadResources.chapieMe;
        Gdx.input.setInputProcessor(stage);
        
        camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
        
        botones.add(exit);
        botones.add(rankings);
        botones.add(start);       
        
        rankings.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		game.setScreen(new ShowScoreScreen (game));		
        	}
        });
        
        exit.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		System.exit(1);
        	}
        });
        
        start.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		newGame();
        	}			
        });
        
        int i=0;
        for (Button b: botones){
        	b.setPosition(camera.viewportWidth/2-b.getWidth()/2, camera.viewportHeight/2+i-100);
        	stage.addActor(b);
        	i=i+85;
        }
    }

    @Override
    public void render(float delta) {
        startGameTimer += delta;

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        
        batch.setProjectionMatrix(camera.combined);
        
        stage.getBatch().begin();
        stage.getBatch().draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
        stage.getBatch().end();
        stage.draw();
    }
    
    @Override
   	public void resize(int width, int height) {
   		viewport.update(width, height);
   	}
    
    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
    }
}