package main.se.tevej.game.view.gui;

import com.badlogic.gdx.Gdx;

import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TUiElement;

/**
 * The gui for the game control. It only has one button for the user to go to the main menu.
 * The game will automatically save the world when the user presses this button.
 */
public class GameControlsGui {

    private TTable controlsTable;

    public GameControlsGui(GuiFactory guiFactory, ChangeScreen screenChanger) {
        this.controlsTable = guiFactory.createTable();

        initializeTable();
        populateTable(guiFactory, screenChanger);
    }

    public void update(float deltaTime) {
        controlsTable.update(deltaTime);
    }

    public void render() {
        controlsTable.render();
    }

    private void initializeTable() {
        this.controlsTable
            .grid(1, 2)
            .positionX(Gdx.graphics.getWidth() - 100)
            .positionY(Gdx.graphics.getHeight() - 25);
    }

    private void populateTable(GuiFactory guiFactory, ChangeScreen screenChanger) {
        this.controlsTable
            .addElement(createMainMenuButton(guiFactory, screenChanger))
            .width(200)
            .height(50);
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
