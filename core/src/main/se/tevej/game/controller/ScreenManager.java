package main.se.tevej.game.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.input.OrderedInputMultiplexer;
import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.libgdximplementation.InputLibgdxFactory;
import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.libgdximplementation.GameRenderingLibgdxFactory;
import main.se.tevej.game.view.gui.ChangeScreen;
import main.se.tevej.game.view.gui.DigitScreens;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.libgdximplementation.GuiLibgdxFactory;

public class ScreenManager extends ApplicationAdapter {

    private ChangeScreen screenChanger;
    private DigitScreen currentScreen;

    public ScreenManager() {
        super();
    }

    @Override
    public void create() {
        screenChanger = createScreenChanger();
        screenChanger.changeScreen(DigitScreens.MAIN_MENU);
    }

    @Override
    public void render() {
        currentScreen.updateAndRender(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        currentScreen.dispose();
    }

    private ChangeScreen createScreenChanger() {
        OrderedInputMultiplexer inputMultiplexer = new OrderedInputMultiplexer();

        GameRenderingFactory renderingFactory = new GameRenderingLibgdxFactory();
        GuiFactory guiFactory = new GuiLibgdxFactory(inputMultiplexer);
        InputFactory inputFactory = new InputLibgdxFactory(inputMultiplexer);
        GameIo gameIo = new GameIo();

        return new ChangeScreen() {
            @Override
            public void changeScreen(DigitScreens digitScreen) {
                if (currentScreen != null) {
                    currentScreen.dispose();
                }

                inputMultiplexer.clear();

                switch (digitScreen) {
                    case PLAY:
                        currentScreen = createPlayScreen(
                            screenChanger,
                            renderingFactory,
                            guiFactory,
                            inputFactory,
                            gameIo
                        );
                        break;
                    case MAIN_MENU:
                        currentScreen = createMainMenuScreen(screenChanger, guiFactory, gameIo);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private PlayScreen createPlayScreen(ChangeScreen screenChanger,
                                        GameRenderingFactory renderingFactory,
                                        GuiFactory guiFactory,
                                        InputFactory inputFactory, GameIo gameIo) {
        return new PlayScreen(
            screenChanger,
            renderingFactory,
            guiFactory,
            inputFactory,
            gameIo
        );
    }

    private MainMenuScreen createMainMenuScreen(ChangeScreen screenChanger,
                                                GuiFactory guiFactory, GameIo gameIo) {
        return new MainMenuScreen(
            screenChanger,
            guiFactory,
            gameIo
        );
    }

}
