package proyect.robots.utils;

import com.badlogic.gdx.math.Rectangle;

public class Crect extends Rectangle{

	private static final long serialVersionUID = 1L;
	public String id;
	
	public Crect(Rectangle r, String id){
		set(r);
		this.id=id;
	}
	
	public Crect() {
	}

	public void setId(String id){
		this.id=id;
	}
	
	public String getId(){
		return id;
	}
}
