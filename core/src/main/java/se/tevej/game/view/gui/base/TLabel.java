package main.java.se.tevej.game.view.gui.base;

public interface TLabel extends TUiElement {
    TLabel text(String text);

    void setColor(float red, float green, float blue, float alpha);
}
