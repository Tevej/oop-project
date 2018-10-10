package se.tevej.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import main.se.tevej.game.Game;

import java.awt.*;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        config.width = screenSize.width / 2;
        config.height = screenSize.height / 2;
        new LwjglApplication(new Game(), config);
    }
}
