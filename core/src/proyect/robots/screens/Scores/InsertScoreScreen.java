package proyect.robots.screens.Scores;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.screens.MyScreen;
import proyect.robots.screens.GameScreen.PlayScreen;
import proyect.robots.utils.Constants;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.saveGame.PlayerSave;
import proyect.robots.utils.saveGame.PlayerSaverArray;
import proyect.robots.utils.saveGame.ScoreManager;

public class InsertScoreScreen extends MyScreen {
	float startGameTimer = 0;
	public Game game;
	public TextButton insert, delete, showScores;
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
    public TextureRegion box, insName;
    public ImageButton up1, down1, up2, down2, up3, down3;
    public PlayScreen screen;
    
    public int cont1=0, cont2=0, cont3=0; 
    //, PlayScreen scren
	public InsertScoreScreen(final Game game, PlayScreen screen) {
		super(game);
		this.game=game;		
		this.screen=screen;
		this.batch= screen.batch;
		stage = new Stage();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
 		viewport.apply();
 		
 		camera.position.x=camera.viewportWidth/2;
 		camera.position.y=camera.viewportHeight/2;
 		camera.update();
 		fondo = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/saveBack.png")));
 		  Gdx.input.setInputProcessor(stage);	 	
	 		letImg = LoadResources.getScoreLetters();
	 		 box = LoadResources.box;	 
	 		LoadResources.loadNumbers();
	 		numbers = LoadResources.numbers;
	 		
	 		score = new Texture(Gdx.files.internal("screensAssets/score.png"));
	 		insName = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/insName.png")));
	 		
	 		arletters = new Array<String>();
	 		arletters.addAll(Constants.letters);
	 		
	 		button = LoadResources.button;	 		
	 		TextButton.TextButtonStyle btSt = new TextButton.TextButtonStyle();
	        btSt.up = new TextureRegionDrawable(new TextureRegion(button));
	        btSt.font=font;
	        
	             
	      showScores = new TextButton("OK", btSt);
	        showScores.addListener(new ClickListener(){
	        	@Override
	        	public void clicked (InputEvent event, float x, float y){
	        		saveData();
	        		((MyGame) game).setScreenWithFade(new ShowScoreScreen(game), 3);
	        		//game.setScreen(new ShowScoreScreen(game));
	        	}
	        });
	        stage.addActor(showScores);
	        showScores.setPosition(camera.viewportWidth/2-button.getRegionWidth()/2-25, 50);
	        
	        initArrows();
	        
	        box = LoadResources.box;	        
	}

	 @Override
	    public void show() {		    	
	         
	       
	 }

	 int bs=96;
	 int ls=48;
	 
	  @Override
	    public void render(float delta) {
		  batch.setProjectionMatrix(camera.combined);
		  Gdx.input.setInputProcessor(stage);
	     
	        
	       batch.begin();
	      // batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
	       batch.draw(fondo, 0, 0, camera.viewportWidth, camera.viewportHeight);
	       batch.draw(insName, camera.viewportWidth/2-insName.getRegionWidth()/2-25, camera.viewportHeight-100);
	       batch.draw(box, (camera.viewportWidth/2)-bs-ls-24, camera.viewportHeight-200-bs-5,bs,bs );
	       batch.draw(letImg.get(cont1), (camera.viewportWidth/2)-bs-ls+12, camera.viewportHeight-200-ls-5-12,24,24);
	       batch.draw(box, (camera.viewportWidth/2)-ls-24, camera.viewportHeight-200-bs-5,bs,bs );
	       batch.draw(letImg.get(cont2), (camera.viewportWidth/2)-ls+12, camera.viewportHeight-200-ls-5-12,24,24);
	       batch.draw(box, (camera.viewportWidth/2)+24, camera.viewportHeight-200-bs-5,bs,bs );
	       batch.draw(letImg.get(cont3), (camera.viewportWidth/2)+ls+12, camera.viewportHeight-200-ls-5-12,24,24);
	      // TODO 
	       batch.draw(score, 400, 250, score.getWidth()*1.5f, score.getHeight()*1.5f);
	        Array<Integer> nums =splitNumbers(screen.score);
	        int posx=(int) (400+score.getWidth()*1.5f)+20;
	        for (Integer i:nums){
    			batch.draw(numbers.get(i), posx, 262,24,24);
    			posx+=24;
    		}
	       batch.end();
	      //  drawScores();
	        stage.draw();	
	    }
	  // TODO
	  public void saveData(){
		  StringBuilder sb = new StringBuilder();
		  sb.append(arletters.get(cont1)+arletters.get(cont2)+arletters.get(cont3));
		  MyGame.saver.addSave(new PlayerSave(sb.toString(), screen.score, Integer.parseInt(screen.mapName.substring(screen.mapName.length() - 1))));
		  ScoreManager.savePlayers(MyGame.saver);
		  screen.beated=false;
		  screen.score=0;
	  }
	    
		public void initArrows() {
			TextureRegionDrawable arr = new TextureRegionDrawable(LoadResources.arrow);
			TextureRegionDrawable ad = new TextureRegionDrawable(LoadResources.arrowU);
	      
			down1 = new ImageButton(arr);
			down1.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont1= getIndex(cont1, -1);
					System.out.println("contador 1 : "+cont1);
				}
			});
			down1.setPosition((camera.viewportWidth/2)-bs-ls, camera.viewportHeight-200-bs-10-ls);
			up1 = new ImageButton(ad);
			up1.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont1= getIndex(cont1, 1);
					System.out.println("contador 1 : "+cont1);
				}
			});
			up1.setPosition((camera.viewportWidth/2)-bs-ls, camera.viewportHeight-200);
			
			
			down2 = new ImageButton(arr);
			down2.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont2= getIndex(cont2, -1);
				}
			});
			down2.setPosition((camera.viewportWidth/2)-ls, camera.viewportHeight-200-bs-10-ls);
			up2 = new ImageButton(ad);
			up2.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont2= getIndex(cont2, 1);
				}
			});
			up2.setPosition((camera.viewportWidth/2)-ls, camera.viewportHeight-200);
			
			down3 = new ImageButton(arr);
			down3.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont3= getIndex(cont3, -1);
				}
			});
			down3.setPosition((camera.viewportWidth/2)+ls, camera.viewportHeight-200-bs-10-ls);
			up3 = new ImageButton(ad);
			up3.addListener(new ClickListener(){
				@Override
				public void clicked (InputEvent event, float x, float y){
					cont3= getIndex(cont3, 1);
				}
			});
			up3.setPosition((camera.viewportWidth/2)+ls, camera.viewportHeight-200);
			
			stage.addActor(up1);
			stage.addActor(down1);
			stage.addActor(up2);
			stage.addActor(down2);
			stage.addActor(up3);
			stage.addActor(down3);
		}
		
		public int getIndex(int acc, int s){
			int cont=acc;
				cont+=s;				
				if(cont>arletters.size){
					cont=0;
				}else if(cont<0){
					cont=arletters.size;
				}
				return cont;
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
}
 
