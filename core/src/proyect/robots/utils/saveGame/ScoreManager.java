package proyect.robots.utils.saveGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import proyect.robots.MyGame;

public class ScoreManager {
	
	public static void openFile(){
		FileHandle file = Gdx.files.local("puntuacions.json");
		
		if(file.exists()){ 
			MyGame.saver=showPlayers(); 
		}		
	}
	
	public static PlayerSaverArray showPlayers() {
		PlayerSaverArray saver = new PlayerSaverArray();
		JsonReader jsonreader = new JsonReader();
		JsonValue base = jsonreader.parse(Gdx.files.local("puntuacions.json"));
		for(JsonValue entry:base){
			for(JsonValue e: entry){
				String name = e.getString("name");
				int points = e.getInt("points");
				int stage = e.getInt("stage");
				PlayerSave ps = new PlayerSave(name, points, stage);
				saver.addSave(ps);
			}
		}
		return saver;
	}	

	public static void savePlayers(PlayerSaverArray arr) {
		Json json = new Json();
		json = new Json();
		String pnts = json.toJson(arr);
		FileHandle file1 = Gdx.files.local("puntuacions.json");
		file1.writeString(pnts,false);
	}	
}
