package main.se.tevej.game.controller.input.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.view.ViewManager;

// A class to translate the user inputs to camera changes.
public class CameraController implements OnDraggedListener,
    OnMouseClickedListener, OnTappedListener {

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

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final float zoomStep = 0.1f;

    public CameraController(ViewManager view, float startX, float startY, TMouse mouse,
                            TKeyBoard keyboard, int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.view = view;
        mouse.addDraggedListener(this);
        mouse.addClickedListener(this);
        keyboard.addTappedListener(this);
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

            clampCameraPos();
            updateCameraPos();
            updatePrev(x, y);
        }
    }

    private void updateCameraPos() {
        view.setPosition(cameraPosX, cameraPosY);
    }

    private void updatePrev(float x, float y) {
        Vector2 pos = getScreenToWorldCoord(x, y);
        prevX = pos.x;
        prevY = pos.y;
    }

    public Vector2 getScreenToWorldCoord(float screenX, float screenY) {
        float pixelPerTile = view.getPixelPerTile();
        float zoom = view.getZoomMultiplier();
        float screenTileX = screenX / (pixelPerTile * zoom);
        float screenTileY = screenY / (pixelPerTile * zoom);

        float screenTileXOffset = screenTileX + cameraPosX;
        float screenTileYOffset = (float) Gdx.app.getGraphics().getHeight()
            / (pixelPerTile * zoom) + (cameraPosY - screenTileY);

        return new Vector2(screenTileXOffset, screenTileYOffset);
    }

    public float getCameraPosX() {
        return cameraPosX;
    }

    public float getCameraPosY() {
        return cameraPosY;
    }

    @Override
    public void onTapped(TKeyBoard keyBoard, TKey button) {
        switch (button) {
            case KEY_Z:
                zoomIn();
                break;
            case KEY_X:
                zoomOut();
                break;
            default:
                break;
        }
    }

    private void zoomIn() {
        view.zoom(view.getZoomMultiplier() + zoomStep);
        zoom();
    }

    private void zoomOut() {
        view.zoom(view.getZoomMultiplier() - zoomStep);
        zoom();
    }

    private void zoom() {
        newPosX = cameraPosX;
        newPosY = cameraPosY;
        clampCameraPos();
        updateCameraPos();
    }

    private void clampCameraPos() {
        // Make sure we cannot move outside the map.
        if (newPosX < 0) {
            newPosX = 0;
        }
        if (newPosY < 0) {
            newPosY = 0;
        }

        float pixelPerTile = view.getPixelPerTile() * view.getZoomMultiplier();

        float maxX = worldWidth - ((float) Gdx.app.getGraphics().getWidth() / pixelPerTile);
        float maxY = worldHeight - ((float) Gdx.app.getGraphics().getHeight() / pixelPerTile);

        if (newPosX > maxX) {
            newPosX = maxX;
        }
        if (newPosY > maxY) {
            newPosY = maxY;
        }

        cameraPosX = newPosX;
        cameraPosY = newPosY;
    }

}
