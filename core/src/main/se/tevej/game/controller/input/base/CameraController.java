package main.se.tevej.game.controller.input.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import main.se.tevej.game.view.ViewManager;


public class CameraController implements OnDraggedListener, OnMouseClickedListener {

    private ViewManager view;

    // Current camera position in world coordinates!
    private float prevX;
    private float prevY;
    private float newPosX;
    private float newPosY;
    private float cameraPosX;
    private float cameraPosY;
    private int worldWidth;
    private int worldHeight;

    public CameraController(ViewManager view, float startX, float startY, TMouse mouse,
                            int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.view = view;
        mouse.addDraggedListener(this);
        mouse.addClickedListener(this);
        cameraPosX = startX;
        cameraPosY = startY;
        view.setPosition(cameraPosX, cameraPosY);
    }

    private void calculateNewPos(float x, float y) {
        Vector2 pos = getScreenToWorldCoord(x, y);

        float deltaX = pos.x - prevX;
        float deltaY = pos.y - prevY;

        // - because 0, 0 is in the top left corner
        newPosX = cameraPosX - deltaX;
        newPosY = cameraPosY - deltaY;
    }

    @Override
    public void onClicked(TMouse mouse, TKey button) {
        updatePrev(mouse.getX(), mouse.getY());
    }

    @Override
    public void onDragged(TKey button, float x, float y) {
        if (button == TKey.MOUSE_LEFT) {
            calculateNewPos(x, y);

            if (newPosX < 0) {
                newPosX = 0;
            }
            if (newPosY < 0) {
                newPosY = 0;
            }

            float pixelPerTile = view.getPixelPerTile();

            float maxX = worldWidth - ((float) Gdx.app.getGraphics().getWidth() / pixelPerTile);
            float maxY = worldHeight - ((float) Gdx.app.getGraphics().getHeight() / pixelPerTile);

            if (newPosX > maxX) {
                newPosX = maxX;
            }
            if (newPosY > maxY) {
                newPosY = maxY;
            }

            updateCameraPos();

            updatePrev(x, y);
        }
    }

    private void updateCameraPos() {
        cameraPosX = newPosX;
        cameraPosY = newPosY;
        view.setPosition(cameraPosX, cameraPosY);
    }

    private void updatePrev(float x, float y) {
        Vector2 pos = getScreenToWorldCoord(x, y);
        prevX = pos.x;
        prevY = pos.y;
    }

    public Vector2 getScreenToWorldCoord(float screenX, float screenY) {
        float pixelPerTile = view.getPixelPerTile();
        float screenTileX = screenX / pixelPerTile;
        float screenTileY = screenY / pixelPerTile;

        float screenTileXOffset = screenTileX + cameraPosX;
        float screenTileYOffset = (float) Gdx.app.getGraphics().getHeight()
                / pixelPerTile + (cameraPosY - screenTileY);
        return new Vector2(screenTileXOffset, screenTileYOffset);
    }

    public float getCameraPosX() {
        return cameraPosX;
    }

    public float getCameraPosY() {
        return cameraPosY;
    }
}
