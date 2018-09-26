package se.tevej.game.view.rendering.ui;

public interface TTable {

    TCell addElement(TUIElement element);

    TTable x(float x);
    TTable y(float y);

    void update();
    void render();

}
