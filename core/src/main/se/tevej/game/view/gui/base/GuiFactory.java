package main.se.tevej.game.view.gui.base;

/**
 * To try to separate libgdx and the game, we use the abstract factory pattern. This
 * factory is used to create every element and the table in the game.
 */
public interface GuiFactory {

    TButton createButton();

    TLabel createLabel();

    TTable createTable();

    TImage createImage();

}

