package main.se.tevej.game.controller.input.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

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
