package main.se.tevej.game.view.gui.base;

/**
 * An element that display text with support of changing the color.
 */
public interface TLabel extends TUiElement {
    TLabel text(String text);

    void setColor(float red, float green, float blue, float alpha);
}
