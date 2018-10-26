package main.java.se.tevej.game.view.gui.base;

// Not our fault that the libgdx class has too many methods ¯\_(ツ)_/¯
@SuppressWarnings("PMD.TooManyMethods")

/**
 * TTable organizes every element, lays them out in a grid, updates them and renders them.
 */
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

    TTable visible(boolean visible);

    void update(float deltaTime);

    void render();

}
