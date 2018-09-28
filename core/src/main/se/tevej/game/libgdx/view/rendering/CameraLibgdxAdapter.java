package main.se.tevej.game.libgdx.view.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import main.se.tevej.game.view.rendering.TCamera;

public class CameraLibgdxAdapter extends OrthographicCamera implements TCamera {

    @Override
    public float getViewportWidth() {
        return super.viewportWidth;
    }

    @Override
    public float getViewportHeight() {
        return super.viewportHeight;
    }

    @Override
    public void setViewport(float viewportWidth, float viewportHeight) {
        super.viewportWidth = viewportWidth;
        super.viewportHeight = viewportHeight;
    }

    @Override
    public float getPositionX() {
        return super.position.x;
    }

    @Override
    public float getPositionY() {
        return super.position.y;
    }

    @Override
    public void setPosition(float x, float y) {
        super.position.set(x, y, 0);
    }

}
