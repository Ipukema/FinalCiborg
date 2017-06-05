package proyect.robots.utils.loaders;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import proyect.robots.MyGame;
import proyect.robots.entities.Enemies.LVL1.BigRobot;
import proyect.robots.entities.Enemies.LVL1.Drone1;
import proyect.robots.entities.Enemies.LVL1.Drone2;
import proyect.robots.entities.Enemies.LVL1.FinalBoss1;
import proyect.robots.entities.Enemies.LVL2.Eye;
import proyect.robots.entities.Enemies.LVL2.Finalboss2;
import proyect.robots.entities.Enemies.LVL2.Worm;
import proyect.robots.screens.GameScreen.PlayScreen;
import proyect.robots.utils.Crect;

public class MapGenerator{
    public static Array<Rectangle> ground ;
    public static Array<Rectangle> ceiling;
    public static  Array<Rectangle> right;
    public static  Array<Rectangle> left;
    public static Array<Rectangle> death;
    public static Array<Rectangle> block;
    
    public static ArrayList<Array<Rectangle>> mapBounds;
    
	public PlayScreen screen;
	
	public static Music stage1Song, stage2Song;
	
	public Game game;
	public Music currentSong;
	public Array<Music> songs = new Array<Music>();
	
	public MapGenerator(Game game){		
		this.game=game;
		 ground = new Array<Rectangle>();
		 ceiling = new Array<Rectangle>();
		 right = new Array<Rectangle>();
		 left = new Array<Rectangle>();
		 death = new Array<Rectangle>();
		 block = new Array<Rectangle>();
		 mapBounds = new ArrayList<Array<Rectangle>>();	
		 songs.add(SoundAssets.stage1Song);
		 songs.add(SoundAssets.stage2Song);
		 songs.add(SoundAssets.introSong);
	}
	
	public void loadScreen(String map, String playerColor, float x, float y){
		if(SoundAssets.introSong.isPlaying()){
			SoundAssets.introSong.stop();
		}
		SoundAssets.loadPlayerAudio();
		PlayScreen screen = new PlayScreen(this.game, map, playerColor, x, y);
		loadCollisions(screen);
		
		if(map.equals("stage1")){
			LoadMap1(screen);			
		}else if(map.equals("stage2")){
			LoadMap2(screen);
		}else if(map.equals("stage3")){
			
		}
		((MyGame) game).setScreenWithFade(screen, 4);
	}
	
	
	
	private void LoadMap2(PlayScreen screen2) {
		currentSong= SoundAssets.stage2Song;
		try{
			for(Music m:songs){
				if(m.isPlaying()){
					m.stop();
				}			
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			addLvl2Enemies(screen2);
			screen2.setSong(currentSong);
		}		
	}

	public void LoadMap1(PlayScreen screen){
		SoundAssets.introSong.stop();
		currentSong= SoundAssets.stage1Song;
		try{
			for(Music m:songs){
				if(m.isPlaying()){
					m.stop();
				}			
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{		
			addLvl1Enemies(screen);		
			screen.setSong(currentSong);
		}
	}
	public void addLvl2Enemies(PlayScreen screen){
		new Worm(screen, 70, 210, "ground");
		new Worm(screen, 250, 195, "ground");
		new Worm(screen, 397, 195, "ground");
		new Worm(screen, 661, 210, "ground");
		new Worm(screen, 916, 140, "ground");
		new Worm(screen, 1150, 190, "ground");
		new Worm(screen, 502, 85, "ground");
		new Worm(screen, 675, 85, "ground");
		new Worm(screen, 1380, 190, "ground");
		new Worm(screen, 1660, 130, "ground");
		new Worm(screen, 1850, 160, "ground");
		new Worm(screen, 2070, 260, "ground");
		new Worm(screen, 1940, 260, "ground");
		//ceiling worms
		new Worm(screen, 770, 150, "ceiling");
		new Worm(screen, 1200, 240, "ceiling");
		new Worm(screen, 1385, 210, "ceiling");
		new Worm(screen, 1700, 170, "ceiling");
		//eyes:
		new Eye(screen, 556, 200);
		new Eye(screen, 710, 170);
		new Eye(screen, 265, 130);
		new Eye(screen, 425, 130);
		new Eye(screen, 615, 100);	
		new Eye(screen, 1830, 170);
		new Eye(screen, 1690, 170);
		new Eye(screen, 1200, 240);
		new Eye(screen, 1300, 180);
		new Eye(screen, 1515, 200);
		new Eye(screen, 200, 150);
		//and boss:
		new Finalboss2(screen, 2060, 80);
	}
	
	public void addLvl1Enemies(PlayScreen screen) {
		new BigRobot(screen,71,195);
		new BigRobot(screen,300,68);
		new BigRobot(screen,300,230);
		new BigRobot(screen,405,225);
		new BigRobot(screen,515,95);
		new BigRobot(screen,595,85);
		new BigRobot(screen,685,80);
		new BigRobot(screen,866,145);
		new BigRobot(screen,1000,70);
		new BigRobot(screen,1010,130);
		new BigRobot(screen,1200,130);
		new BigRobot(screen,595,210);
		new BigRobot(screen,900,245);
		new BigRobot(screen,980,245);
		new BigRobot(screen,1315,40);
		new BigRobot(screen,1485,35);
		new BigRobot(screen,1770,55);
		new BigRobot(screen,1700,55);
		new BigRobot(screen,1700,230);
		new BigRobot(screen,1860,260);
		new BigRobot(screen,2000,260);
		
		new Drone1(screen, 150, 180);
		new Drone1(screen, 440, 90);
		new Drone1(screen, 631, 245);
		new Drone1(screen, 1174, 245);
		new Drone1(screen, 1600, 100);
		new Drone1(screen, 1090, 64);
		
		
		new Drone2(screen, 520, 245);
		new Drone2(screen, 770, 245);
		new Drone2(screen, 1060, 245);
		new Drone2(screen, 1360, 90);
		new Drone2(screen, 1700, 100);
		new Drone2(screen, 1600, 250);
		new Drone2(screen, 1500, 80);
		new Drone2(screen, 1975, 95);
		
		new FinalBoss1(screen, 2684, 200);
	}
	
	 public void loadCollisions (PlayScreen screen){
		 
	    	MapObjects objects = screen.map.getLayers().get("Ground").getObjects();
		 
	    	 for(MapObject object : objects) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Ground");
	    			 screen.ground.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.ground);
	    	 
	    	 MapObjects objects1 = screen.map.getLayers().get("Ceiling").getObjects();
	    	 
	    	 for(MapObject object : objects1) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Ceiling");
	    			 screen.ceiling.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.ceiling);
	    	 
	    	 MapObjects objects3 = screen.map.getLayers().get("Left").getObjects();
	    	 
	    	 for(MapObject object : objects3) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Left");
	    			 screen.left.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.left);
	    	 
	    	 MapObjects objects4 = screen.map.getLayers().get("Right").getObjects();
	    	 
	    	 for(MapObject object : objects4) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Right");
	    			 screen.right.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.right);
	    	 
	    	 MapObjects objects5 = screen.map.getLayers().get("Block").getObjects();
	    	 
	    	 for(MapObject object : objects5) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Block");
	    			 screen.block.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.block);
	    	 
	    	 MapObjects objects6 = screen.map.getLayers().get("Death").getObjects();
	    	 
	    	 for(MapObject object : objects6) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Death");
	    			 screen.death.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.death);
	    	 
	    	 MapObjects objects7 = screen.map.getLayers().get("Respawn").getObjects();
			 
	    	 for(MapObject object : objects7) {
	    		 if (object instanceof RectangleMapObject) {
	    			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
	    			 Crect r= new Crect(rect, "Respawn");
	    			 screen.respawn.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.respawn);
	    }
}
