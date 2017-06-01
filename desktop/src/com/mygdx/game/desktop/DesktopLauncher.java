package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import proyect.robots.MyGame;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Robots";
		config.width=1200;
		config.height=720;
		config.foregroundFPS = 60;
		new LwjglApplication(new MyGame(), config);		
	}
}