package io.github.plenglin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.plenglin.magix.Magix;
import org.lwjgl.Sys;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DesktopLauncher {
	public static void main (String[] arg) {
        //System.out.printf("log props %s\n", System.getProperty("java.util.logging.config.file"));

		//LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINEST);

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.foregroundFPS = 60;
		config.backgroundFPS = 30;

		new LwjglApplication(new Magix(), config);
	}
}
