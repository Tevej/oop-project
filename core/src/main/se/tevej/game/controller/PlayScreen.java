package main.se.tevej.game.controller;

import java.io.IOException;
import java.util.List;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.time.OnTimeChangeListener;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gui.ChangeScreen;
import main.se.tevej.game.view.gui.base.GuiFactory;

public class PlayScreen extends DigitScreen implements OnTimeChangeListener {

    private ModelManager model;
    private ViewManager view;
    private GameIo gameIo;
    private float timeMultiplier;

    public PlayScreen(ChangeScreen screenChanger, GameRenderingFactory renderingFactory,
                      GuiFactory guiFactory, InputFactory inputFactory, GameIo gameIo) {
        super(screenChanger);
        timeMultiplier = 1f;
        this.gameIo = gameIo;

        initializeMvc(renderingFactory, guiFactory, inputFactory);
    }

    @Override
    public void updateAndRender(float deltaTime) {
        float newDeltaTime = deltaTime * timeMultiplier;
        model.update(newDeltaTime);
        view.update(newDeltaTime);
    }

    @Override
    public void dispose() {
        try {
            gameIo.save(model);
        } catch (IOException e) {
            System.out.println("Saving didn't work");
        }
    }

    private void initializeMvc(GameRenderingFactory renderingFactory,
                               GuiFactory guiFactory, InputFactory inputFactory) {
        int worldWidth = 100;
        int worldHeight = 100;

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

        view = new ViewManager(model, renderingFactory, guiFactory, screenChanger);
        new ControllerManager(view, model, worldWidth, worldHeight, inputFactory, this);
    }

    @Override
    public void updateTimeMultipler(float newMultiplier) {
        timeMultiplier = newMultiplier;
    }

}
