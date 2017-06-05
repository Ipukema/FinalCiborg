package proyect.robots.screens.Scores;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.MyScreen;
import proyect.robots.screens.Menus.MenuScreen;
import proyect.robots.screens.Menus.StageSelectScreen;
import proyect.robots.utils.Constants;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.saveGame.PlayerSave;
import proyect.robots.utils.saveGame.PlayerSaverArray;

public class ShowScoreScreen extends MyScreen {
	
	float startGameTimer = 0;
	public Game game;
	public TextButton backMenu;
	public TextButton stgSelect;
	public Stage stage;
    public TextureRegion fondo;   
    public Viewport viewport;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public ArrayList<TextureRegion> letImg;
    public ArrayList<TextureRegion> numbers;   
    public Texture name, score, stg;
    public int cont=0;
    public int accept = 0;
    public PlayerSave savers;
    public PlayerSaverArray puntuaciones;
    public Array<PlayerSave> arSaves;
    public Array<String> arletters;
    public TextureRegion button;
    public BitmapFont font;
    
	public ShowScoreScreen(Game game) {
		super(game);
		this.game=game;
	}
	
    @Override
    public void show() {
    	 font = new BitmapFont();
    	 batch = new SpriteBatch();
    	 stage = new Stage();
         fondo = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/scoreShowBack.png")));
         Gdx.input.setInputProcessor(stage);
         
         camera = new OrthographicCamera();
 		
 		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
 		viewport.apply();
 		
 		camera.position.x=camera.viewportWidth/2;
 		camera.position.y=camera.viewportHeight/2;
 		camera.update();
 		
 		letImg = LoadResources.getScoreLetters();
 		
 		name = new Texture(Gdx.files.internal("screensAssets/name.png"));
 		score = new Texture(Gdx.files.internal("screensAssets/score.png"));
 		stg = new Texture(Gdx.files.internal("screensAssets/stage.png"));

 		puntuaciones=MyGame.saver;
 		LoadResources.loadNumbers();
 		numbers = LoadResources.numbers;
 		arSaves=puntuaciones.getSaves();
 		arletters = new Array<String>();
 		arletters.addAll(Constants.letters);
 		
 		button = LoadResources.button;
        TextButton.TextButtonStyle btSt = new TextButton.TextButtonStyle();
        btSt.up = new TextureRegionDrawable(new TextureRegion(button));
        btSt.font=font;
        backMenu = new TextButton("BACK MENU", btSt);
        backMenu.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		((MyGame) game).setScreenWithFade(new MenuScreen(game),3);	
        	}
        });
        stage.addActor(backMenu);
        backMenu.setPosition(200, 50);
        
        stgSelect = new TextButton("STAGE SELECT", btSt);
        stgSelect.addListener(new ClickListener(){
        	@Override
        	public void clicked (InputEvent event, float x, float y){
        		stageSelect();
        	}
        });
        stgSelect.setPosition(camera.viewportWidth-200-button.getRegionWidth(), 50);
        stage.addActor(stgSelect);
    }   
    
    private void stageSelect() {
    	((MyGame) game).setScreenWithFade(new StageSelectScreen(game),3);	
    }
     
    public void drawScores(){
    	int cont=0;
    	batch.begin();
    	float posy= (camera.viewportHeight-120);    	
    	arSaves.sort();
    	for(PlayerSave s: arSaves){
    		String name = s.getName();
    		int p = s.getPoints();
    		int st=s.stage;
    		Array<Integer> nums = new Array<Integer>();
    		for (char ch: name.toCharArray()) {
    			char c=Character.toUpperCase(ch);
    			String sr= Character.toString(c);
    			nums.add(arletters.lastIndexOf(sr,false));
    		}
    		float posx=150;
    		for (Integer i:nums){    			
    			batch.draw(letImg.get(i), posx, posy,24,24);
    			posx+=24;
    		}
    		nums=splitNumbers(p);
    		posx=300+(this.name.getWidth()*2);
    		for (Integer i:nums){
    			batch.draw(numbers.get(i), posx, posy,24,24);
    			posx+=24;
    		}
    		nums=splitNumbers(st);
    		posx=450+(this.name.getWidth()*2)+(score.getWidth()*2);
    		for (Integer i:nums){
    			batch.draw(numbers.get(i), posx, posy,24,24);
    			posx+=24;
    		}
    	
    		posy-=40;
    		cont++;
    		if(cont>=10){
    			break;
    		}
    	}    	
    	batch.end();
    }
    
    public  Array<Integer> splitNumbers(Integer number) {

        Array<Integer> result = new Array<Integer>();
        String s =Integer.toString(number);

        for (int i = 0; i < s.length(); i++) {
        	if(s.charAt(i)=='0'){
        		result.add(9);
        	}else{
        		result.add(s.charAt(i) - '1');
        	}        	
        }
        return result;
      }
    
    @Override
    public void render(float delta) {
        startGameTimer += delta;

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);        
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.draw(name, 150, camera.viewportHeight-70, name.getWidth()*2, name.getHeight()*2);
        batch.draw(score, 300+(name.getWidth()*2), camera.viewportHeight-70, score.getWidth()*2, score.getHeight()*2);
        batch.draw(stg, 450+(name.getWidth()*2)+(score.getWidth()*2), camera.viewportHeight-70, stg.getWidth()*2, stg.getHeight()*2);
        batch.end();
        drawScores();
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
        stage.dispose();
    }	
}