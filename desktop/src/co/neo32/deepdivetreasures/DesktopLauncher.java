package co.neo32.deepdivetreasures;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import co.neo32.deepdivetreasures.DeepDiveTreasures;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(320 * 2, 224 * 2);
		config.setTitle("Deep Dive Treasures");
		new Lwjgl3Application(new DeepDiveTreasures(), config);
	}
}
