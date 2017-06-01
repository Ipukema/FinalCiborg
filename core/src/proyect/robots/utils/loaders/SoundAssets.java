package proyect.robots.utils.loaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundAssets {
	//the songs for game:
	public static Music introSong, stage1Song, stage2Song;
	//the effects for game:
	public static Music saber1;
	
	//the shoot efects:
	
	//the player effects: 
	public static Music footSteps, misile1;
	public static Sound playerShoot, misile2, playerShootHit, playerMisileHit;
	//the enemies effects: 
	
	
	
	public static void loadMenuAudios(){
		introSong = Gdx.audio.newMusic(Gdx.files.internal("Sounds/music/stage1.mp3"));
		saber1= Gdx.audio.newMusic(Gdx.files.internal("Sounds/effects/Lightsaber1.mp3"));
	}
	
	public static void loadStage1Audio(){
		stage1Song = Gdx.audio.newMusic(Gdx.files.internal("Sounds/music/intro.mp3"));
		
	}
	
	public static void loadPlayerAudio(){
		playerShoot = Gdx.audio.newSound(
				Gdx.files.internal("Sounds/effects/playerEffects/playerShoot.mp3"));
		footSteps = Gdx.audio.newMusic(
				Gdx.files.internal("Sounds/effects/playerEffects/playerFootSteps.mp3"));
		misile1 = Gdx.audio.newMusic(
				Gdx.files.internal("Sounds/effects/playerEffects/missile1.mp3"));
		misile2 = Gdx.audio.newSound(
				Gdx.files.internal("Sounds/effects/playerEffects/missile2.mp3"));
		playerShootHit = Gdx.audio.newSound(
				Gdx.files.internal("Sounds/effects/playerEffects/playerShootHit.mp3"));
		playerMisileHit = Gdx.audio.newSound(
				Gdx.files.internal("Sounds/effects/playerEffects/playerMissileHit.mp3"));
		
	}
}
