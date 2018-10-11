package main.se.tevej.game.view.rendering.ui;

public interface TTable {

    TCell addElement(TUIElement element);

    TTable x(float x);

    TTable y(float y);

    TTable grid(int rows, int columns);

    TTable debug(boolean debug);

    void update(float deltaTime);

    void render();

}
