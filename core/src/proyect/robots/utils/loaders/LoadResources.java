package proyect.robots.utils.loaders;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import proyect.robots.utils.CustomAnim;


public class LoadResources {
	//animations:
	public static Animation dust, shield;
	public static CustomAnim blueEnergy, fireSmoke, greenEnergy, energyCollapse, redBlood;
	//MENUS 1  and 2:
	public static TextureRegion intro, introText, button, chapieMe, fondo;
	//stage selector:
	public static TextureRegion st1, st2, st3,stageSelectBack;
	//char selec:
	public static TextureRegion char1, char2, char3, char4, charback, goldButt, silverButt;
	//pause screen:
	public static TextureRegion pauseBack, roff, ron;
	//HUD:
	public static TextureRegion redbar, hud;
	//Settings: 
	public static TextureRegion logo, bar, note, fx, setBack, backButton;
	 //Death screen:
	public static TextureRegion deathFondo, contText;
	public static Array<TextureRegion> deathCount;
	//ScoreScreens:
	public static ArrayList<TextureRegion> numbers;
	public static TextureRegion arrow, arrowU, box;
	
	
	public static ArrayList<TextureRegion> getScoreLetters(){
		ArrayList<TextureRegion> letras = new ArrayList<TextureRegion>();
		Texture letters= new Texture("screensAssets/scoreText.png");
		
		TextureRegion[][] tmp = TextureRegion.split(letters, 
				letters.getWidth() / 9,
				letters.getHeight() / 3);
		TextureRegion[] prov = new TextureRegion[9*3];
		int index=0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j <=8; j++) {
				prov[index++]=tmp[i][j];			
			}
		}
		for (int i=0;i<prov.length;i++){
			letras.add(prov[i]);
		}
		
		arrow = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/dwn.png")));
		arrowU = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/up.png")));
		box = new TextureRegion(new Texture(Gdx.files.internal("screensAssets/Blank.png")));
		return letras;		
	}
	
	public static void LoadMenu2(){
		TextureAtlas atlas= new TextureAtlas("Atlas/miscellanous2.txt");
		fondo = new TextureRegion(atlas.findRegion("pencilChappie"));
	}
	
	 public static void loadNumbers(){
		 	numbers= new ArrayList<TextureRegion>();
	    	Texture num =new Texture("screensAssets/n1.png");
	    	int column=5;
	    	int row=2;
	    	TextureRegion[][] nums = TextureRegion.split(num, num.getWidth()/column, num.getHeight()/row); 
	    	int index =0;
	    	TextureRegion[] s= new TextureRegion[column*row];
	    	for (int i=0; i<row; i++) {
	    	    for (int j=0; j<column; j++) {
	    	        s[index++]=nums[i][j];
	    	    }
	    	}    
	    	for(int i=0;i<s.length;i++){
	    		numbers.add(s[i]);
	    	}
	    }
	 
	public static void LoadDeathScreen(){
		deathCount = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("screensAssets/deathScreen.txt");
		deathFondo = new TextureRegion(atlas.findRegion("muerto"));
		contText = new TextureRegion(atlas.findRegion("conttext"));
		int col=5;
		int row=2;
		TextureRegion reg = new TextureRegion(atlas.findRegion("deathCount"));
		TextureRegion[][] nums=reg.split(reg.getRegionWidth()/col, reg.getRegionHeight()/row);
		for (int i=0; i<row; i++) {
    	    for (int j=0; j<col; j++) {
    	    	deathCount.add(nums[i][j]);
    	    }
    	} 		
	}
	
	public static void loadPauseMenu(){
		TextureAtlas atlas= new TextureAtlas("Atlas/miscellanous2.txt");
		roff = new TextureRegion(atlas.findRegion("IconSmall_Off"));
		ron = new TextureRegion(atlas.findRegion("IconSmall_On"));
		pauseBack = new TextureRegion(atlas.findRegion("pauseBack"));
	}
	
	public static void loadSettingsScreen(){
		TextureAtlas atlas= new TextureAtlas("Atlas/miscellanous2.txt");
		logo = new TextureRegion(atlas.findRegion("settingsBack"));
		bar = new TextureRegion(atlas.findRegion("sliderBar"));
		note = new TextureRegion(atlas.findRegion("note"));
		fx = new TextureRegion(atlas.findRegion("fx"));
		setBack = new TextureRegion(atlas.findRegion("settings"));
		backButton = new TextureRegion(atlas.findRegion("back"));
	}
	
	public static void LoadMisc1(){
		TextureAtlas atlas= new TextureAtlas("Atlas/miscellanous.txt");
		button =  new TextureRegion(atlas.findRegion("OrangeSquareButton"));
		intro=  new TextureRegion(atlas.findRegion("portadaEdit"));
		introText =  new TextureRegion(atlas.findRegion("introText"));
		chapieMe =  new TextureRegion(atlas.findRegion("ChappieMe"));
		st1 =  new TextureRegion(atlas.findRegion("planetMech"));
		st2 =  new TextureRegion(atlas.findRegion("planetbio"));
		st3 =  new TextureRegion(atlas.findRegion("planetThird"));
		stageSelectBack =  new TextureRegion(atlas.findRegion("stageSelectBack"));
		LoadBloods();
	}
	
	public static void LoadHUD(){
		TextureAtlas atlas= new TextureAtlas("Atlas/miscellanous2.txt");
		redbar =  new TextureRegion(atlas.findRegion("redbar"));
		hud = new TextureRegion(atlas.findRegion("hud"));		
	}	
	
	//carga de los elementos del selector de color del personaje:
	public static void LoadCharSelector(){
		TextureAtlas atlas = new TextureAtlas("screensAssets/heroSelector.txt");
		char1 = new TextureRegion(atlas.findRegion("blueChar"));
		char2 = new TextureRegion(atlas.findRegion("greyChar"));
		char3 = new TextureRegion(atlas.findRegion("yellowChar"));
		char4 = new TextureRegion(atlas.findRegion("redChar"));
		charback = new TextureRegion(atlas.findRegion("back"));
		goldButt = new TextureRegion(atlas.findRegion("goldenButton"));
		silverButt = new TextureRegion(atlas.findRegion("silverButton"));
	}
	
	//Metodo de carga de los efectos de sangre
	public static void LoadBloods(){
		
		
		Texture bloodSheet= new Texture("Enemies/particles.png");
		
		TextureRegion[][] tmp = TextureRegion.split(bloodSheet, 
				bloodSheet.getWidth() / 8,
				bloodSheet.getHeight() / 22);

		// Place the regions into a 1D array in the correct order, starting from the top 
		// left, going across first. The Animation constructor requires a 1D array.
		Array<TextureRegion> walkFrames = new Array<TextureRegion>();
		int cont=0;
		for (int i = 0; i < 22; i++) {
			for (int j = 0; j < 8; j++) {
				//walkFrames[index++] = tmp[i][j];
				walkFrames.add(tmp[i][j]);
				if(cont==31){
					blueEnergy = new CustomAnim(0.055f, walkFrames);					
					walkFrames.clear();					
				}else if(cont==71){
					fireSmoke = new CustomAnim(2.5f, walkFrames);
					walkFrames.clear();
				}else if (cont==113){
					greenEnergy = new CustomAnim(2.5f, walkFrames);
					walkFrames.clear();
				}else if (cont==153){
					energyCollapse = new CustomAnim(0.025f, walkFrames);
					walkFrames.clear();
				}else if(cont == 175){
					redBlood= new CustomAnim(0.025f, walkFrames);
					walkFrames.clear();
				}
				cont++;
			}
		}		
	}	
	//Carga las animaciones del player:
	
	public static ArrayList<CustomAnim> loadPlayerAnimations(String color){
		
		ArrayList<CustomAnim> anims = new ArrayList<CustomAnim>();
		Array<TextureRegion> frames = new Array<TextureRegion>();
		TextureAtlas atlas= new TextureAtlas("Personaje/"+color+"Player.txt");
		
		//climbAnims:
		for (int i=1;i<=3;i++){
			StringBuilder sb2 = new StringBuilder("climbR");
			TextureRegion reg = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(reg);
		}
		CustomAnim climbR = new CustomAnim(0.09f,frames,"climbR");
		anims.add(climbR);
		CustomAnim climbL = new CustomAnim(0.09f,frames,"climbL");
		anims.add(climbL);
		frames.clear();		
		//diyingAnims:diying1
		for (int i=1;i<=4;i++){
			StringBuilder sb2 = new StringBuilder("diying");
			TextureRegion reg = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(reg);
		}
		CustomAnim diyingR = new CustomAnim(0.14f,frames,"diyingR");
		anims.add(diyingR);
		CustomAnim diyingL = new CustomAnim(0.14f,frames,"diyingL");
		anims.add(diyingL);
		frames.clear();		
		//Hold Sides anims:
		//hold side shooting down
		TextureRegion reg = new TextureRegion(atlas.findRegion("holdRSD"));
		frames.add(reg);
		CustomAnim holdRSD = new CustomAnim(0.09f,frames,"holdRSD");
		anims.add(holdRSD);
		CustomAnim holdLSD = new CustomAnim(0.09f,frames,"holdLSD");
		anims.add(holdLSD);
		frames.clear();
		//hold side shooting diagonal down opposite
		TextureRegion reg1 = new TextureRegion(atlas.findRegion("holdRSDL"));
		frames.add(reg1);
		CustomAnim holdRSDL = new CustomAnim(0.09f,frames,"holdRSDL");
		anims.add(holdRSDL);
		CustomAnim holdLSDR = new CustomAnim(0.09f,frames,"holdLSDR");
		anims.add(holdLSDR);
		frames.clear();
		//hold side shooting diagonal down same side
		TextureRegion reg2 = new TextureRegion(atlas.findRegion("holdRSDR"));
		frames.add(reg2); 
		CustomAnim holdRSDR = new CustomAnim(0.09f,frames,"holdRSDR");
		anims.add(holdRSDR);
		CustomAnim holdLSDL = new CustomAnim(0.09f,frames,"holdLSDL");
		anims.add(holdLSDL);
		frames.clear();
		//hold side shooting opposite side
		TextureRegion reg3 = new TextureRegion(atlas.findRegion("holdRSL"));
		frames.add(reg3);
		CustomAnim holdRSL = new CustomAnim(0.09f,frames,"holdRSL");
		anims.add(holdRSL);
		CustomAnim holdLSR = new CustomAnim(0.09f,frames,"holdLSR");
		anims.add(holdLSR);
		frames.clear();
		//hold side shooting same side
		TextureRegion reg4 = new TextureRegion(atlas.findRegion("holdRSR"));
		frames.add(reg4);
		CustomAnim holdRSR = new CustomAnim(0.09f,frames,"holdRSR");
		anims.add(holdRSR);
		CustomAnim holdLSL = new CustomAnim(0.09f,frames,"holdLSL");
		anims.add(holdLSL);
		frames.clear();
		//hold side shooting up
		TextureRegion reg5 = new TextureRegion(atlas.findRegion("holdRSU"));
		frames.add(reg5);
		CustomAnim holdRSU = new CustomAnim(0.09f,frames,"holdRSU");
		anims.add(holdRSU);
		CustomAnim holdLSU = new CustomAnim(0.09f,frames,"holdLSU");
		anims.add(holdLSU);
		frames.clear();
		//hold side shooting up opposite
		TextureRegion reg6 = new TextureRegion(atlas.findRegion("holdRSUL"));
		frames.add(reg6);
		CustomAnim holdRSUL = new CustomAnim(0.09f,frames,"holdRSUL");
		anims.add(holdRSUL);
		CustomAnim holdLSUR = new CustomAnim(0.09f,frames,"holdLSUR");
		anims.add(holdLSUR);
		frames.clear();
		//hold side shooting up same side
		TextureRegion reg7 = new TextureRegion(atlas.findRegion("holdRSUR"));
		frames.add(reg7);
		CustomAnim holdRSUR = new CustomAnim(0.09f,frames,"holdRSUR");
		anims.add(holdRSUR);
		CustomAnim holdLSUL = new CustomAnim(0.09f,frames,"holdLSUL");
		anims.add(holdLSUL);
		frames.clear();
		//hold up animations: 
		//hold up shooting up same side
		TextureRegion reg8 = new TextureRegion(atlas.findRegion("holdSUR"));
		frames.add(reg8);
		CustomAnim holdSUR = new CustomAnim(0.09f,frames,"holdUSUR");
		anims.add(holdSUR);
		CustomAnim holdSUL = new CustomAnim(0.09f,frames,"holdUSUL");
		anims.add(holdSUL);
		frames.clear();
		//hold up shooting down
		TextureRegion reg9 = new TextureRegion(atlas.findRegion("holdUSD"));
		frames.add(reg9);
		CustomAnim holdUSDfaceR = new CustomAnim(0.09f,frames,"holdUSDfaceR");
		anims.add(holdUSDfaceR);
		CustomAnim holdSUDfaceL = new CustomAnim(0.09f,frames,"holdSUDfaceL");
		anims.add(holdSUDfaceL);
		frames.clear();
		//hold up shooting down same side looking
		TextureRegion regs = new TextureRegion(atlas.findRegion("holdUSDR"));
		frames.add(regs);
		CustomAnim holdUSDR = new CustomAnim(0.09f,frames,"holdUSDR");
		anims.add(holdUSDR);
		CustomAnim holdUSDL = new CustomAnim(0.09f,frames,"holdUSDL");
		anims.add(holdUSDL);
		frames.clear();
		//hold up shooting same side looking
		TextureRegion regs2 = new TextureRegion(atlas.findRegion("holdUSR"));
		frames.add(regs2);
		CustomAnim holdUSR = new CustomAnim(0.09f,frames,"holdUSR");
		anims.add(holdUSR);
		CustomAnim holdUSL = new CustomAnim(0.09f,frames,"holdUSL");
		anims.add(holdUSL);
		frames.clear();
		//hold up shooting up
		TextureRegion regs3 = new TextureRegion(atlas.findRegion("holdUSU"));
		frames.add(regs3);
		CustomAnim holdUSUfaceR = new CustomAnim(0.09f,frames,"holdUSUfaceR");
		anims.add(holdUSUfaceR);
		CustomAnim holdUSUfaceL = new CustomAnim(0.09f,frames,"holdUSUfaceL");
		anims.add(holdUSUfaceL);
		frames.clear();
		//hold up moving animations:
		for (int i=1;i<=3;i++){
			StringBuilder sb2 = new StringBuilder("holdUmoveR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1);
		}
		CustomAnim holdUmoveR = new CustomAnim(0.09f,frames,"holdUmoveR");
		anims.add(holdUmoveR);
		CustomAnim holdUmoveL = new CustomAnim(0.09f,frames,"holdUmoveL");
		anims.add(holdUmoveL);
		frames.clear();
		//jumping animations:
		for (int i=1;i<=4;i++){
			StringBuilder sb2 = new StringBuilder("jumpR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1);
		}
		CustomAnim jumpR = new CustomAnim(0.09f,frames,"jumpR");
		anims.add(jumpR);
		CustomAnim jumpL = new CustomAnim(0.09f,frames,"jumpL");
		anims.add(jumpL);
		frames.clear();
		//running anims:
		//running gun down
		for (int i=1;i<=6;i++){
			StringBuilder sb2 = new StringBuilder("runRSDR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1);
		}
		CustomAnim runRSDR = new CustomAnim(0.09f,frames,"runRSDR");
		anims.add(runRSDR);
		CustomAnim runLSDL = new CustomAnim(0.09f,frames,"runLSDL");
		anims.add(runLSDL);
		frames.clear();
		//running gun middle
		for (int i=1;i<=6;i++){
			StringBuilder sb2 = new StringBuilder("runRSR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1); 
		}
		CustomAnim runRSR = new CustomAnim(0.09f,frames,"runRSR");
		anims.add(runRSR);
		CustomAnim runLSL = new CustomAnim(0.09f,frames,"runLSL");
		anims.add(runLSL);
		frames.clear();
		//running gun up
		for (int i=1;i<=6;i++){
			StringBuilder sb2 = new StringBuilder("runRSUR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1);
		}
		CustomAnim runRSUR = new CustomAnim(0.09f,frames,"runRSUR");
		anims.add(runRSUR);
		CustomAnim runLSUL = new CustomAnim(0.09f,frames,"runLSUL");
		anims.add(runLSUL);
		frames.clear();
		//stand anim:
		TextureRegion regs4 = new TextureRegion(atlas.findRegion("stand"));
		frames.add(regs4);
		CustomAnim stand = new CustomAnim(0.09f,frames,"stand");
		anims.add(stand);
		frames.clear();
		//stand and shooting:
		//shooting down:
		for (int i=1;i<=2;i++){
			StringBuilder sb2 = new StringBuilder("standRSD");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1);
		}
		CustomAnim standRSD = new CustomAnim(0.19f,frames,"standRSD");
		anims.add(standRSD);
		CustomAnim standLSD = new CustomAnim(0.19f,frames,"standLSD");
		anims.add(standLSD);
		frames.clear();
		//shooting down diagonal same side looking:
		for (int i=1;i<=2;i++){
			StringBuilder sb2 = new StringBuilder("standRSDR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1); 
		}
		CustomAnim standRSDR = new CustomAnim(0.09f,frames,"standRSDR");
		anims.add(standRSDR);
		CustomAnim standLSDL = new CustomAnim(0.09f,frames,"standLSDL");
		anims.add(standLSDL);
		frames.clear();
		//shooting same side looking:
		for (int i=1;i<=2;i++){
			StringBuilder sb2 = new StringBuilder("standRSR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1); 
		}
		CustomAnim standRSR = new CustomAnim(0.09f,frames,"standRSR");
		anims.add(standRSR);
		CustomAnim standLSL = new CustomAnim(0.09f,frames,"standLSL");
		anims.add(standLSL);
		frames.clear();
		//shooting up:
		for (int i=1;i<=2;i++){
			StringBuilder sb2 = new StringBuilder("standRSU");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1); 
		}
		CustomAnim standRSU = new CustomAnim(0.09f,frames,"standRSU");
		anims.add(standRSU);
		CustomAnim standLSU = new CustomAnim(0.09f,frames,"standLSU");
		anims.add(standLSU);
		frames.clear();
		//shooting up diagonal same side looking:
		for (int i=1;i<=2;i++){
			StringBuilder sb2 = new StringBuilder("standRSUR");
			TextureRegion regs1 = new TextureRegion(atlas.findRegion(sb2.append(i).toString()));
    		frames.add(regs1); 
		}
		CustomAnim standRSUR = new CustomAnim(0.09f,frames,"standRSUR");
		anims.add(standRSUR);
		CustomAnim standLSUL = new CustomAnim(0.09f,frames,"standLSUL");
		anims.add(standLSUL);
		frames.clear();
		return anims;		
	}
}