package main.java.se.tevej.game.view.gui.base;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

/**
 *  Used to either add an input processor for the game rendering or the gui.
 */
public interface InputProcessorListener {
    void addGameRenderingInputProcessor(InputProcessor inputProcessor);

    void addGuiInputProcessor(InputAdapter inputProcessor);
}
