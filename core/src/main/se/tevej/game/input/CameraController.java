package main.se.tevej.game.input;

import com.badlogic.gdx.math.Vector2;
import main.se.tevej.game.input.enums.TButton;
import main.se.tevej.game.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;
import main.se.tevej.game.view.View;

import static main.se.tevej.game.view.View.pixelPerTile;


public class CameraController implements OnDraggedListener, OnClickedListener {

    private TMouse mouse;
    private View view;

    // Current camera position in world coordinates!
    private float prevX;
    private float prevY;
    private float newPosX;
    private float newPosY;
    private float cameraPosX;
    private float cameraPosY;

    private float worldWidth;
    private float worldHeight;

    public CameraController(View view, InputLibgdxFactory factory,
                            float startX, float startY, int worldWidth, int worldHeight) {
        this.view = view;
        this.mouse = factory.createMouse();
        mouse.addDraggedListener(this);
        mouse.addClickedListener(this);
        cameraPosX = startX;
        cameraPosY = startY;
        view.setPosition(cameraPosX, cameraPosY);
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    void calculateNewPos( float x, float y) {
        Vector2 pos = getScreenToWorldCoord(x, y);

        float deltaX = pos.x - prevX;
        float deltaY = pos.y - prevY;

        // - because 0, 0 is in the top left corner
        newPosX = cameraPosX - deltaX;
        newPosY = cameraPosY + deltaY;
    }

    @Override
    public void onClicked(TMouse mouse, TButton button) {
        updatePrev(mouse.getX(), mouse.getY());
    }

    @Override
    public void onDragged(TMouse mouse, TButton button, float x, float y) {
        if (button == TButton.MOUSE_LEFT) {
            calculateNewPos(x, y);

            // System.out.println("new pos: " + newPosX + ", " + newPosY + " world size: " + worldWidth + ", " + worldHeight);

            // TODO: Change worldWidth/worldHeight to ex: worldWidth - (screen.size.x * pixelPerTile)!
            if (newPosX >= 0 && newPosX <= worldWidth && newPosY >= 0 && newPosY <= worldHeight) {
                cameraPosX = newPosX;
                cameraPosY = newPosY;
                System.out.println(cameraPosX + ", " + cameraPosY);
                view.setPosition(cameraPosX, cameraPosY);
            }

            updatePrev(x, y);
        }
    }

    private void updatePrev(float x, float y) {
        Vector2 pos = getScreenToWorldCoord(x, y);
        prevX = pos.x;
        prevY = pos.y;
    }

    private Vector2 getScreenToWorldCoord(float x, float y) {
        x /= pixelPerTile;
        y /= pixelPerTile;
        x += cameraPosX;
        y += cameraPosY;
        return new Vector2(x, y);
    }
}
