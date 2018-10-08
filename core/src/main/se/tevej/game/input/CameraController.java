package main.se.tevej.game.input;

import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;
import main.se.tevej.game.view.rendering.TCamera;

public class CameraController {

    private TCamera camera;
    private TMouse mouse;

    public CameraController(TCamera camera, InputLibgdxFactory factory) {
        this.camera = camera;
        this.mouse = factory.createMouse();
    }
}
