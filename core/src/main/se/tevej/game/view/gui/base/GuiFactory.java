package main.se.tevej.game.view.gui.base;

public interface GuiFactory {

    TButton createButton();

    TLabel createLabel();

    TTable createTable();

    TImage createImage();

}

