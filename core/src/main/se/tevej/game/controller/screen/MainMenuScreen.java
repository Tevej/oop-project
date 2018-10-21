package main.se.tevej.game.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import main.se.tevej.game.io.GameIo;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TTable;

public class MainMenuScreen extends DigitScreen {

    private final TTable table;

    public MainMenuScreen(ChangeScreen changeScreen, GuiFactory guiFactory, GameIo gameIo) {
        super(changeScreen);
        table = guiFactory.createTable();

        populateTable(guiFactory, gameIo);
    }

    @Override
    public void update(float deltaTime) {
        table.update(deltaTime);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        table.render();
    }

    @Override
    public void dispose() {

    }

    private void populateTable(GuiFactory guiFactory, GameIo gameIo) {

        if (gameIo.hasSavedGame()) {
            table
                .grid(3, 1);

            table
                .addElement(createPlayButton(guiFactory, "Continue last game", false, gameIo))
                .width(300)
                .height(50);

            table
                .addElement(createPlayButton(guiFactory, "Start new game", true, gameIo))
                .width(300)
                .height(50);
        } else {
            table
                .grid(2, 1);

            table
                .addElement(createPlayButton(guiFactory, "Play", false, gameIo))
                .width(300)
                .height(50);
        }

        table
            .addElement(createExitButton(guiFactory))
            .width(300)
            .height(50);

        table
            .positionX(Gdx.graphics.getWidth() / 2.0f)
            .positionY(Gdx.graphics.getHeight() - 200);
    }

    private TButton createPlayButton(GuiFactory guiFactory, String text, boolean newGame, GameIo gameIo) {
        return guiFactory
            .createButton()
            .text(text)
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    if(newGame){
                        gameIo.removeSavedGame();
                    }
                    MainMenuScreen.super.screenChanger.changeScreen(DigitScreens.PLAY);
                }
            });
    }

    private TButton createExitButton(GuiFactory guiFactory) {
        return guiFactory
            .createButton()
            .text("Exit")
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    Gdx.app.exit();
                }
            });
    }

}
