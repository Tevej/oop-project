package main.se.tevej.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.ControllerManager;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class GameManager extends ApplicationAdapter {

    private ModelManager model;
    private ViewManager view;
    private ControllerManager controller;

    public GameManager() {
        super();
    }

    @Override
    public void create() {
        Options options = new Options(100, 100);
        model = new ModelManager(options);
        view = new ViewManager(model);
        controller = new ControllerManager(options, view, model);

        model.init();
        view.init();
        controller.init();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        model.update(deltaTime);
        view.update(deltaTime);
        controller.update(deltaTime);
    }

}
