package main.se.tevej.game.view.gui.base;

public interface TTable {

    TCell addElement(TUiElement element);

    TTable backgroundColor(float red, float green, float blue, float alpha);

    TTable positionX(float x);

    TTable positionY(float y);

    TTable grid(int rows, int columns);

    TTable debug(boolean debug);

    TTable alignLeft();

    TTable alignCenter();

    TTable setPadding(float amount);

    void update(float deltaTime);

    void render();

}
