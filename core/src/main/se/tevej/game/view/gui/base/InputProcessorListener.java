package main.se.tevej.game.view.gui.base;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;

public interface InputProcessorListener {
    void addGameRenderingInputProcessor(InputProcessor inputProcessor);

    void addGuiInputProcessor(InputAdapter inputProcessor);
}
