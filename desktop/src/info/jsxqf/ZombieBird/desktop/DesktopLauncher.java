package info.jsxqf.ZombieBird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.jsxqf.ZombieBird.ZBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Jump";
        config.useGL30 = false;
        config.width = 700;
        config.height = 400;

		new LwjglApplication(new ZBGame(), config);
	}
}
