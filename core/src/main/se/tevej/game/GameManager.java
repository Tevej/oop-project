package main.se.tevej.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.ControllerManager;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class GameManager extends ApplicationAdapter {

    private ModelManager model;
    private ViewManager view;

    public GameManager() {
        super();
    }

    @Override
    public void create() {
        int worldWidth = 100;
        int worldHeight = 100;
        model = new ModelManager(worldWidth, worldHeight);
        view = new ViewManager(model);
        new ControllerManager(view, model, worldWidth, worldHeight);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        model.update(deltaTime);
        view.update(deltaTime);
    }

}
