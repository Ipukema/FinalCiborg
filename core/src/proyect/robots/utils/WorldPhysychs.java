package proyect.robots.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import proyect.robots.screens.PlayScreen;

public class WorldPhysychs {
	public World world;
	public TiledMap map;
	public BodyDef bdef;
	public PolygonShape shape;
	public FixtureDef fdef;
	public Body body;
	
	
	public WorldPhysychs(PlayScreen screen, World world){
		this.world = world;
		map = screen.getMap();
		bdef = new BodyDef();
		shape = new PolygonShape();
		fdef = new FixtureDef();
		GenerateBodys("Ground");
		GenerateBodys("Ceiling");
		GenerateBodys("Left");
		GenerateBodys("Right");
		GenerateBodys("Death");	
		GenerateBodys("Block");
	}	
	
	public void GenerateBodys(String capa){
		for(MapObject object: map.getLayers().get(capa).getObjects()){
			 Rectangle rect = ((RectangleMapObject) object).getRectangle();
			 bdef.type = BodyDef.BodyType.StaticBody;
			 bdef.position.set((rect.getX() + rect.getWidth() / 2) / 100, (rect.getY() + rect.getHeight() / 2) / 100);
			 body = world.createBody(bdef);

           shape.setAsBox(rect.getWidth() / 2 /100, rect.getHeight() / 2 / 100);
           fdef.shape = shape;
           body.createFixture(fdef);
		}		
	}	
}