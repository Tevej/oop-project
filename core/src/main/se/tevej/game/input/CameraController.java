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

        prevX = camera.getPositionX();
        prevY = camera.getPositionY();

        mouse.addDraggedListener(new OnDraggedListener() {
            @Override
            public void onDragged(TMouse mouse, TButton button, float x,float y) {
                if (button == TButton.MOUSE_LEFT) {
                    calculateDeltaVector(x,y);
                    applyVectorToCamera();
                    prevX = x;
                    prevX = y;
                    System.out.println("X, Y: " + x + "  " + y);
                    System.out.println("Mouse: " + mouse.getX() + " " + mouse.getY());
                    System.out.println("Camera: " + camera.getPositionX() + " " + camera.getPositionY());
                    System.out.println("---------------------------------");
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

}
