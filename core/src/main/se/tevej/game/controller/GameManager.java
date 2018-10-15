package main.se.tevej.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class GameManager extends ApplicationAdapter implements OnTimeChangeListener {

    private ModelManager model;
    private ViewManager view;
    private float timeMultiplier;

    public GameManager() {
        super();
        timeMultiplier = 1f;
    }

    @Override
    public void create() {
        int worldWidth = 100;
        int worldHeight = 100;
        model = new ModelManager(worldWidth, worldHeight);
        OrderedInputMultiplexer inputMultiplexer = new OrderedInputMultiplexer();
        view = new ViewManager(model, inputMultiplexer);
        new ControllerManager(view, model, worldWidth, worldHeight, inputMultiplexer, this);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime() * timeMultiplier;

        model.update(deltaTime);
        view.update(deltaTime);
    }

    @Override
    public void updateTimeMultipler(float newMultiplier) {
        timeMultiplier = newMultiplier;
    }
}
