package main.se.tevej.game.controller.screen;

import java.io.IOException;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.ControllerManager;
import main.se.tevej.game.controller.OnTimeChangeListener;
import main.se.tevej.game.controller.OrderedInputMultiplexer;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class PlayScreen extends DigitScreen implements OnTimeChangeListener {

    private ModelManager model;
    private ViewManager view;
    private GameIo gameIo;
    private float timeMultiplier;

    public PlayScreen(ChangeScreen screenChanger){
        super(screenChanger);
        timeMultiplier = 1f;

        initalizeMVC();
    }

    @Override
    public void update(float deltaTime){
        model.update(deltaTime);
        view.update(deltaTime);
    }

    @Override
    public void render(){

    }

    @Override
    public void dispose(){
        try {
            gameIo.save(model);
        } catch (IOException e) {
            System.out.println("Saving didn't work");
        }
    }

    private void initalizeMVC(){
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
        new ControllerManager(view, model, worldWidth, worldHeight, inputMultiplexer, this);
    }

    @Override
    public void updateTimeMultipler(float newMultiplier) {
        timeMultiplier = newMultiplier;
    }

}
