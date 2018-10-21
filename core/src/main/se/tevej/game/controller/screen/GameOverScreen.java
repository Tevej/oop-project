package main.se.tevej.game.controller.screen;

import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TTable;

public class GameOverScreen extends DigitScreen {

    private TTable table;

    public GameOverScreen(ChangeScreen screenChanger, GuiFactory guiFactory) {
        super(screenChanger);

        table = guiFactory.createTable();
    }

    @Override
    public void update(float deltaTime) {
        table.update(deltaTime);
    }

    @Override
    public void render() {
        table.render();
    }

    @Override
    public void dispose() {

    }

}
