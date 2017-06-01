package proyect.robots;


import com.badlogic.gdx.Game;

import proyect.robots.screens.IntroScreen;
import proyect.robots.utils.loaders.LoadResources;
import proyect.robots.utils.saveGame.PlayerSaverArray;
import proyect.robots.utils.saveGame.ScoreManager;

public class MyGame extends Game {
	public static int LVL;
	public static float musicValue, fxValue;
	public static int GW = 1200, GH=720;
	public static PlayerSaverArray saver = new PlayerSaverArray();
	
	@Override
	public void create () {
		musicValue =1;
		fxValue=1;
		ScoreManager.openFile();
		LoadResources.LoadMisc1();
		setScreen(new IntroScreen(this));
	}
}