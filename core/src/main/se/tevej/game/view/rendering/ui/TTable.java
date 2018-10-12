package main.se.tevej.game.view.rendering.ui;

public interface TTable {

    TCell addElement(TUiElement element);

    TTable getX(float x);

    TTable getY(float y);

    TTable grid(int rows, int columns);

    TTable debug(boolean debug);

    void update(float deltaTime);

    void render();

}
