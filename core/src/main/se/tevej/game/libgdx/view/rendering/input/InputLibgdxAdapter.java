package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import main.se.tevej.game.controller.input.enums.TKey;

import java.util.HashMap;
import java.util.Map;

public abstract class InputLibgdxAdapter {

    public static final Map<Integer, TKey> inputMap = new HashMap<>();

}
