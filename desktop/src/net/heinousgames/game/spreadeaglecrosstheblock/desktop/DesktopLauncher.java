package net.heinousgames.game.spreadeaglecrosstheblock.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.heinousgames.game.spreadeaglecrosstheblock.SpreadEagles;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Spread Eagle Cross the Block";
		config.width = 1334;
		config.height = 750;
		config.addIcon("gfx/moon_mac_icon.png", Files.FileType.Internal);
		config.addIcon("gfx/moon_desktop_icon.png", Files.FileType.Internal);
		config.addIcon("gfx/moon_desktop_icon16x.png", Files.FileType.Internal);

		// make the game run windowed and borderless
//		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
//		config.width = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().width);
//		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;

		new LwjglApplication(new SpreadEagles(new DesktopDateFormatter()), config);
	}
}
