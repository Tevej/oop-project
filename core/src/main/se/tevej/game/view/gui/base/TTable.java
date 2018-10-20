package main.se.tevej.game.view.gui.base;

import java.util.List;

public interface TTable {

    TCell addElement(TUiElement element);

    TTable backgroundColor(float red, float green, float blue, float alpha);

    TTable positionX(float x);

    TTable positionY(float y);

    TTable grid(int rows, int columns);

    TTable alignLeft();

    TTable alignCenter();

    TTable debug(boolean debug);

    TTable padding(float amount);

    void update(float deltaTime);

    void render();

}
