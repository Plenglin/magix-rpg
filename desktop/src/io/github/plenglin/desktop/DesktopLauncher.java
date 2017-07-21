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
        System.out.println("running...");

		LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINEST);

		for (String a: arg){
		    System.out.println(a);
        }
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Magix(), config);
	}
}
