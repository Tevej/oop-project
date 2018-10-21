package main.se.tevej.game.view.gui;

import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.screen.ChangeScreen;
import main.se.tevej.game.controller.screen.DigitScreens;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TUiElement;

public class GameControlsGui {

    private TTable controlsTable;

    public GameControlsGui(GuiFactory guiFactory, ChangeScreen screenChanger) {
        this.controlsTable = guiFactory.createTable();

        populateTable(guiFactory, screenChanger);
    }

    public void update(float deltaTime) {
        controlsTable.update(deltaTime);
    }

    public void render() {
        controlsTable.render();
    }

    private void populateTable(GuiFactory guiFactory, ChangeScreen screenChanger) {
        this.controlsTable.grid(1, 2);

        this.controlsTable
            .addElement(createMainMenuButton(guiFactory, screenChanger))
            .width(200)
            .height(50);

        this.controlsTable
            .positionX(Gdx.graphics.getWidth() - 100)
            .positionY(Gdx.graphics.getHeight() - 25);
    }

    private TUiElement createMainMenuButton(GuiFactory guiFactory, ChangeScreen screenChanger) {
        return guiFactory
            .createButton()
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    screenChanger.changeScreen(DigitScreens.MAIN_MENU);
                }
            })
            .text("Main menu");
    }

}
