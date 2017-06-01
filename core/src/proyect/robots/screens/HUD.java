package proyect.robots.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import proyect.robots.MyGame;
import proyect.robots.entities.player.Player;
import proyect.robots.utils.loaders.LoadResources;

public class HUD {
 	public World world;
 	public SpriteBatch batch;
 	public BitmapFont font;
    public  PlayScreen screen;
    public int w,h, lifes;
    public Stage stage;
    public Viewport viewport;
	private OrthographicCamera camera;
	public TextureRegion redbar, hud;
    
    public HUD(World world){
        this.world = world;   
        LoadResources.LoadHUD();
        
        camera = new OrthographicCamera();
		
		viewport =  new FitViewport(MyGame.GW, MyGame.GH,camera);
		viewport.apply();
		
		camera.position.x=camera.viewportWidth/2;
		camera.position.y=camera.viewportHeight/2;
		camera.update();
		
        redbar = LoadResources.redbar;
        hud = LoadResources.hud;
        
        this.camera.position.x = camera.viewportWidth /2f;
        this.camera.position.y = camera.viewportHeight /2f;
        camera.update();
        batch= new SpriteBatch();
        font = new BitmapFont();
        font.getData().scale(2);
        font.setColor(Color.RED);
    }
    
    public void render(){
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        batch.draw(hud, 0, 0, camera.viewportWidth, camera.viewportHeight );
        batch.draw(redbar, 146, camera.viewportHeight-82, 327*Player.hp, 47  );
        font.draw(batch, Integer.toString(PlayScreen.score), camera.viewportWidth-410, camera.viewportHeight-42);
        batch.end();
        batch.begin();
        font.draw(batch, Integer.toString(Player.rocketCounter),200, camera.viewportHeight-100);
    	batch.end();batch.begin();
    	font.draw(batch, Player.shielded ? "ON" : "OFF", camera.viewportWidth-300, camera.viewportHeight-100);
    	batch.end();       
    }
    
    public void resize(int w, int h){
    	viewport.update(w, h);
    }
}