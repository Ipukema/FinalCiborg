package proyect.robots.screens.Menus;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.MyScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.loaders.MapGenerator;

public class CharSelector extends MyScreen{

	public float startGameTimer = 0;
    public BitmapFont font;
    public SpriteBatch batch;
    public Game game;
    public TextureRegion st1, st2, fondo;
    public TextureRegion pblue, pyellow, pred, pgrey;   
    public ImageButton bt1, bt2, bt3, bt4;    
    public ArrayList<Button> botones;
    public ArrayList<TextureRegion> personajes;
    
    public Stage stage; 
    
    public String playerType, pantalla;
    
    public MapGenerator mapper;
    
    public Viewport viewport;
    private OrthographicCamera camera;
    
	public CharSelector(Game game, String pantalla) {
		super(game);		
		this.pantalla=pantalla;
		this.game=game;
	}
	
	@Override
	public void show(){
		mapper = new MapGenerator(game);
		 batch = new SpriteBatch();
		 playerType = "";
		 botones = new ArrayList<Button>();
		 personajes = new ArrayList<TextureRegion>();
		 LoadResources.LoadCharSelector();
		 fondo = LoadResources.charback;
		 
		 camera = new OrthographicCamera();
			
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		 
		stage=new Stage(viewport);
		
		 st1 = LoadResources.goldButt;
		 st2 = LoadResources.silverButt;
		 final TextureRegionDrawable goldB = new TextureRegionDrawable(new TextureRegion(st1));
		 final TextureRegionDrawable silverB = new TextureRegionDrawable(new TextureRegion(st2));
		
		 bt1 = new ImageButton(goldB, silverB);
		 bt1.setPosition(100, 100);
		 bt1.addListener(new EventListener() {			
			@Override
			public boolean handle(Event event) {
				playerType="bluePlayer";
				return false;
			}
		});
		 
		 bt1.addListener(new ClickListener(){
			 @Override
	        	public void clicked (InputEvent event, float x, float y){
				 	playerType="blue";
				    checkStage();					
	        	}				
	        });
		 
		 bt2 = new ImageButton(goldB, silverB);
		 bt2.setPosition(350, 100);
		 bt2.addListener(new EventListener() {			
			@Override
			public boolean handle(Event event) {
				playerType="grey";
				return false;
			}
		});
		 
		 bt2.addListener(new ClickListener(){
			 @Override
	        	public void clicked (InputEvent event, float x, float y){
				 	playerType="grey";
				 	 checkStage();		
	        	}			
	        });
		 
		 bt3 = new ImageButton(goldB, silverB);
		 bt3.setPosition(600, 100);
		 bt3.addListener(new EventListener() {			
			@Override
			public boolean handle(Event event) {
				playerType="yellow";
				return false;
			}
		});
		 
		 bt3.addListener(new ClickListener(){
			 @Override
	        	public void clicked (InputEvent event, float x, float y){
				 	playerType="yellow";
				 	 checkStage();		
	        	}			
	        });
		 
		 bt4 = new ImageButton(goldB, silverB);
		 bt4.setPosition(850, 100);
		 bt4.addListener(new EventListener() {			
	
			@Override
			public boolean handle(Event event) {
				bt4.background(silverB);				
				return false;				
			}
		});
		 
		 bt4.addListener(new ClickListener(){
			 @Override
	        	public void clicked (InputEvent event, float x, float y){
				 	playerType="red";
				 	 checkStage();		
	        	}			
	        });
		
		 botones.add(bt1);
		 botones.add(bt2);
		 botones.add(bt3);
		 botones.add(bt4);
		 
		 stage.addActor(bt1);
		 stage.addActor(bt2);
		 stage.addActor(bt3);
		 stage.addActor(bt4);
		 
		 pblue = LoadResources.char1;
		 pgrey = LoadResources.char2;
		 pyellow = LoadResources.char3;
		 pred = LoadResources.char4;
		 
		 personajes.add(pblue);
		 personajes.add(pgrey);
		 personajes.add(pyellow);
		 personajes.add(pred);
		 
		 Gdx.input.setInputProcessor(stage);
	}
	
	public void checkStage() {
		if(pantalla.equals("stage1")){
			mapper.loadScreen(pantalla, playerType, 140, 150);
		}else if (pantalla.equals("stage2")){
			mapper.loadScreen(pantalla, playerType, 30, 150);
		}
	}
	
	 @Override
	    public void render (float delta) {
			 Gdx.gl.glClearColor(0f, 0f, 0f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.setProjectionMatrix(camera.combined);
	        
	        batch.begin();
	        batch.draw(fondo, 0, 0,  camera.viewportWidth, camera.viewportHeight); 
	        batch.end();
	        stage.act();
	        stage.draw();
	        batch.begin();
	        for (Button b:botones){
	        	batch.draw(personajes.get(botones.indexOf(b)), b.getX()+13, b.getY()+30, b.getWidth()-30, b.getHeight()-60);
	        }
	        batch.end();	 
	    }
	 
	 @Override
		public void resize(int width, int height) {
			viewport.update(width, height);
		}
	 
	 @Override
	 public void hide(){
		 batch.dispose();
		 stage.dispose();	 
	 }
}