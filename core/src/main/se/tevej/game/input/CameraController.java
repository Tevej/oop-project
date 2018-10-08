package main.se.tevej.game.input;

import main.se.tevej.game.input.enums.TButton;
import main.se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;
import main.se.tevej.game.view.rendering.TCamera;

public class CameraController {

    private TCamera camera;
    private TMouse mouse;

    private float prevX, prevY, deltaX, deltaY;


    public CameraController(TCamera camera, InputLibgdxFactory factory) {
        this.camera = camera;
        this.mouse = factory.createMouse();


        mouse.addDraggedListener(new OnDraggedListener() {
            @Override
            public void onDragged(TMouse mouse, TButton button, float x,float y) {
                if (button == TButton.MOUSE_LEFT) {
                    calculateDeltaVector(x,y);
                    applyVectorToCamera();
                    setPreviousCoordinates(x, y);
                }
            }
        });
    }

    void calculateDeltaVector( float x, float y) {
        deltaX = x - prevX;
        deltaY = y - prevY;
    }

    void applyVectorToCamera() {
        camera.setPosition(prevX - deltaX,prevY - deltaY );
    }

    void setPreviousCoordinates(float x, float y) {
        prevX = x;
        prevX = y;
    }


}
