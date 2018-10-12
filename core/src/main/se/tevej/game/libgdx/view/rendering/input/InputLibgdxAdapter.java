package main.se.tevej.game.libgdx.view.rendering.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

import main.se.tevej.game.controller.input.enums.TButton;

public abstract class InputLibgdxAdapter {

    public InputLibgdxAdapter() {
        super();
    }

    public void addToInputMultiplexer(InputProcessor ip) {
        if (Gdx.input.getInputProcessor() == null) {
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(ip);
            Gdx.input.setInputProcessor(inputMultiplexer);
        } else {
            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.addProcessor(ip);
        }
    }
}
