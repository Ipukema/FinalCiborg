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
import proyect.robots.entities.Enemies.*;
import proyect.robots.screens.PlayScreen;
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
	
	public MapGenerator(Game game){		
		this.game=game;
		 ground = new Array<Rectangle>();
		 ceiling = new Array<Rectangle>();
		 right = new Array<Rectangle>();
		 left = new Array<Rectangle>();
		 death = new Array<Rectangle>();
		 block = new Array<Rectangle>();
		 mapBounds = new ArrayList<Array<Rectangle>>();		 
	}
	
	public void loadScreen(String map, String playerColor){
		SoundAssets.loadPlayerAudio();
		PlayScreen screen = new PlayScreen(this.game, map, playerColor);
		loadCollisions(screen);
		
		if(map.equals("stage1")){
			LoadMap1(screen);
		}else if(map.equals("stage2")){
			LoadMap2(screen);
		}else if(map.equals("stage3")){
			
		}
		game.setScreen(screen);
	}
	
	
	
	private void LoadMap2(PlayScreen screen2) {
		
	}

	public void LoadMap1(PlayScreen screen){
		SoundAssets.loadStage1Audio();
		SoundAssets.introSong.stop();
		SoundAssets.introSong.dispose();
		stage1Song = SoundAssets.stage1Song;
		stage1Song.setLooping(true);
		stage1Song.play();
		stage1Song.setVolume(MyGame.musicValue);
		addLvl1Enemies(screen);		
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
		new BigRobot(screen,1860,240);
		new BigRobot(screen,2000,240);
		
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
	    			 death.add(r);
	             }
	    	 }
	    	 screen.mapBounds.add(screen.death);
	    }
}
