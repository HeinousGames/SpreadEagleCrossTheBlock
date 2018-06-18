package net.heinousgames.game.spreadeaglecrosstheblock.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Spread Eagle Cross the Block";
		config.width = 1334;
		config.height = 750;
		new LwjglApplication(new SpreadEagles(new DesktopDateFormatter()), config);
	}
}
