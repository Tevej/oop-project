package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import main.se.tevej.game.input.enums.TButton;

import java.util.HashMap;
import java.util.Map;

public abstract class InputLibgdxAdapter {

    public static final Map<Integer, TButton> inputMap = new HashMap<>();

    void addToInputMultiplexer(InputProcessor ip) {
        if(Gdx.input.getInputProcessor() == null){
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(ip);
            Gdx.input.setInputProcessor(inputMultiplexer);
        }else{
            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.addProcessor(ip);
        }
    }
}
