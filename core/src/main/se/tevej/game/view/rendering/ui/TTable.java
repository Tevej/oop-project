package main.se.tevej.game.view.rendering.ui;

public interface TTable {

    TCell addElement(TUiElement element);

    TTable positionX(float x);

    TTable positionY(float y);

    TTable grid(int rows, int columns);

    TTable debug(boolean debug);

    void update(float deltaTime);

    void render();

}
