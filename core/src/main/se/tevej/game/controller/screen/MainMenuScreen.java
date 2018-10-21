package main.se.tevej.game.controller.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TTable;

public class MainMenuScreen extends DigitScreen {

    private final TTable table;

    public MainMenuScreen(ChangeScreen changeScreen, GuiFactory guiFactory) {
        super(changeScreen);
        table = guiFactory.createTable();

        populateTable(guiFactory);
    }

    @Override
    public void update(float deltaTime) {
        table.update(deltaTime);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        table.render();
    }

    @Override
    public void dispose() {

    }

    private void populateTable(GuiFactory guiFactory) {
        table
            .grid(2, 1);

        table
            .addElement(createPlayButton(guiFactory))
            .width(300)
            .height(50);

        table
            .addElement(createExitButton(guiFactory))
            .width(300)
            .height(50);

        table
            .positionX(Gdx.graphics.getWidth() / 2.0f)
            .positionY(Gdx.graphics.getHeight() - 200);

    }

    private TButton createPlayButton(GuiFactory guiFactory) {
        return guiFactory
            .createButton()
            .text("Play")
            .addListener(changeScreenOnClick(DigitScreens.PLAY));
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

    private OnButtonClickedListener changeScreenOnClick(DigitScreens digitScreen) {
        return new OnButtonClickedListener() {
            @Override
            public void onClicked() {
                MainMenuScreen.super.screenChanger.changeScreen(digitScreen);
            }
        };
    }

}
