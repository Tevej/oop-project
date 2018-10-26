package main.java.se.tevej.game.view.gui.base;

public interface GuiFactory {

    TButton createButton();

    TLabel createLabel();

    TTable createTable();

    TTextField createTextField();

    TSelectableList createSelectableList();

    TImage createImage();

}

