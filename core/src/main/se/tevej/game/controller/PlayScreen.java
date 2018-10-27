package main.se.tevej.game.controller;

import java.io.IOException;
import java.util.List;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gui.ChangeScreen;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.time.OnTimeChangeListener;

/**
 * PlayScreen is the game, it is here we initialize the MVC, the backbone of the application.
 */
public class PlayScreen extends DigitScreen implements OnTimeChangeListener {

    private ModelManager modelManager;
    private ViewManager viewManager;
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
    public void update(float deltaTime) {
        float newDeltaTime = deltaTime * timeMultiplier;
        modelManager.update(newDeltaTime);
        viewManager.update(newDeltaTime);
    }

    @Override
    public void dispose() {
        try {
            gameIo.save(modelManager);
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
            modelManager = new ModelManager(worldWidth, worldHeight);
        } else {
            modelManager = new ModelManager(worldWidth, worldHeight, entities);
        }

        viewManager = new ViewManager(modelManager, renderingFactory, guiFactory, screenChanger);
        new ControllerManager(viewManager, modelManager, worldWidth,
                worldHeight, inputFactory, this);
    }

    @Override
    public void updateTimeMultiplier(float newMultiplier) {
        timeMultiplier = newMultiplier;
    }

}
