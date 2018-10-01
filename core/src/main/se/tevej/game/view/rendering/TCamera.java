package main.se.tevej.game.view.rendering;

public interface TCamera {

    float getViewportWidth();
    float getViewportHeight();

    void setViewport(float viewportWidth, float viewportHeight);

    float getPositionX();

    float getPositionY();

    void setPosition(float x, float y);

}
