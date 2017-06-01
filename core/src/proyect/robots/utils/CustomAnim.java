package proyect.robots.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
//Extiende de animation y le hemos añadido un par de variables propias.
public class CustomAnim extends Animation{
	public Vector2 pos= new Vector2();
	public String id;
	
	//Constructores
	public CustomAnim(float frameDuration, Array<? extends TextureRegion> keyFrames) {
		super(frameDuration, keyFrames);
	}
	public CustomAnim(float frameDuration, Array<? extends TextureRegion> keyFrames, Vector2 pos){
		super(frameDuration, keyFrames);
		this.pos=pos;
	}
	public CustomAnim(float frameDuration, Array<? extends TextureRegion> keyFrames, String id) {
		super(frameDuration, keyFrames);
		this.id= id;
		pos=null;
	}
	//un setter para darle una posicion propia
	public void setPos(Vector2 pos){
		this.pos=pos;
	}
	
	//y un metodo de clase estatico para operar con los arrays de estos elementos:
	public static CustomAnim getById (ArrayList<CustomAnim> anims, String animation){
		CustomAnim a = null;
		for (CustomAnim an:anims){
			if(an.id.equals(animation)){
				a=an;
			}
		}
		return a;
	}
}