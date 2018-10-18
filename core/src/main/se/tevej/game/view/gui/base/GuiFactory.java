package main.se.tevej.game.view.gui.base;

public interface GuiFactory {

    TButton createButton();

    TLabel createLabel();

    TLabel createLabel(float red, float green, float blue, float alpha);

    TTable createTable();

    TTextField createTextField();

    TSelectableList createSelectableList();

    TImage createImage();

}

