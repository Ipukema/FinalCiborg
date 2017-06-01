package proyect.robots.utils.saveGame;

import com.badlogic.gdx.utils.Array;

public class PlayerSaverArray {
	public Array<PlayerSave> saves;
	
	public PlayerSaverArray(Array<PlayerSave> saves){
		this.saves=saves;
	}

	public PlayerSaverArray() {
		saves = new Array<PlayerSave>();
	}

	public Array<PlayerSave> getSaves() {
		return saves;
	}

	public void setSaves(Array<PlayerSave> saves) {
		this.saves = saves;
	}
	
	public void addSave(PlayerSave ps){
		saves.add(ps);
	}
}
