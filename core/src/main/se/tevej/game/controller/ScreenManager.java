package main.se.tevej.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.libgdximplementation.InputLibgdxFactory;
import main.se.tevej.game.controller.screen.DigitScreen;
import main.se.tevej.game.controller.screen.MainMenuScreen;
import main.se.tevej.game.controller.screen.PlayScreen;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.libgdximplementation.GameRenderingLibgdxFactory;
import main.se.tevej.game.view.gui.ChangeScreen;
import main.se.tevej.game.view.gui.DigitScreens;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.libgdximplementation.GuiLibgdxFactory;

public class ScreenManager extends ApplicationAdapter {

    private ChangeScreen screenChanger;

    private GameRenderingFactory renderingFactory;
    private GuiFactory guiFactory;
    private InputFactory inputFactory;
    private GameIo gameIo;

    private DigitScreen currentScreen;

    public ScreenManager() {
        super();
    }

    @Override
    public void create() {
        OrderedInputMultiplexer inputMultiplexer = new OrderedInputMultiplexer();

        renderingFactory = new GameRenderingLibgdxFactory();
        guiFactory = new GuiLibgdxFactory(inputMultiplexer);
        inputFactory = new InputLibgdxFactory(inputMultiplexer);

        gameIo = new GameIo();

        screenChanger = new ChangeScreen() {
            @Override
            public void changeScreen(DigitScreens digitScreen) {
                if (currentScreen != null) {
                    currentScreen.dispose();
                }

                inputMultiplexer.clear();

                switch (digitScreen) {
                    case PLAY:
                        currentScreen = new PlayScreen(
                            screenChanger,
                            renderingFactory,
                            guiFactory,
                            inputFactory,
                            gameIo
                        );
                        break;
                    case MAIN_MENU:
                        currentScreen = new MainMenuScreen(screenChanger, guiFactory, gameIo);
                        break;
                    default:
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
