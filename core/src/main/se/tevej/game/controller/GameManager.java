package main.se.tevej.game.controller;

import java.io.IOException;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class GameManager extends ApplicationAdapter {

    private ModelManager model;
    private ViewManager view;
    private GameIo gameIo;

    public GameManager() {
        super();
    }

    @Override
    public void create() {
        int worldWidth = 100;
        int worldHeight = 100;

        gameIo = new GameIo();
        List<Entity> entities = null;
        try {
            entities = gameIo.load();
        } catch (IOException ignored) {
            System.out.println("No world created, generating new...");
        }

        if (entities == null || entities.isEmpty()) {
            model = new ModelManager(worldWidth, worldHeight);
        } else {
            model = new ModelManager(worldWidth, worldHeight, entities);
        }

        OrderedInputMultiplexer inputMultiplexer = new OrderedInputMultiplexer();
        view = new ViewManager(model, inputMultiplexer);
        new ControllerManager(view, model, worldWidth, worldHeight, inputMultiplexer);
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();

        model.update(deltaTime);
        view.update(deltaTime);
    }

    @Override
    public void dispose() {
        try {
            gameIo.save(model);
        } catch (IOException e) {
            System.out.println("Saving didn't work");
        }
    }
}
