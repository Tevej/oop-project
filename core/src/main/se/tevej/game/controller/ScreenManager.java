package main.se.tevej.game.controller;

import java.io.IOException;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.libgdximplementation.InputLibgdxFactory;
import main.se.tevej.game.controller.screen.ChangeScreen;
import main.se.tevej.game.controller.screen.DigitScreen;
import main.se.tevej.game.controller.screen.DigitScreens;
import main.se.tevej.game.controller.screen.GameOverScreen;
import main.se.tevej.game.controller.screen.MainMenuScreen;
import main.se.tevej.game.controller.screen.PlayScreen;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.libgdximplementation.GameRenderingLibgdxFactory;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.libgdximplementation.GuiLibgdxFactory;

public class ScreenManager extends ApplicationAdapter{

    private ChangeScreen screenChanger;

    private GameRenderingFactory gameRenderingFactory;
    private GuiFactory guiFactory;
    private InputFactory inputFactory;

    private DigitScreen currentScreen;

    public ScreenManager() {
        super();
    }

    @Override
    public void create() {
        OrderedInputMultiplexer inputMultiplexer = new OrderedInputMultiplexer();

        gameRenderingFactory = new GameRenderingLibgdxFactory();
        guiFactory = new GuiLibgdxFactory(inputMultiplexer);
        inputFactory = new InputLibgdxFactory(inputMultiplexer);

        screenChanger = new ChangeScreen() {
            @Override
            public void changeScreen(DigitScreens digitScreen) {
                switch(digitScreen){
                    case PLAY:
                        currentScreen = new PlayScreen(
                            screenChanger,
                            gameRenderingFactory,
                            guiFactory,
                            inputFactory
                        );
                        break;
                    case MAIN_MENU:
                        currentScreen = new MainMenuScreen(screenChanger, guiFactory);
                        break;
                    case GAME_OVER:
                        currentScreen = new GameOverScreen(screenChanger, guiFactory);
                        break;
                }
            }
        };

        screenChanger.changeScreen(DigitScreens.MAIN_MENU);

    }

    @Override
    public void render() {
        currentScreen.update(Gdx.graphics.getDeltaTime());
        currentScreen.render();
    }

    @Override
    public void dispose() {
        currentScreen.dispose();
    }

}
