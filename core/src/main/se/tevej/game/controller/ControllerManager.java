package main.se.tevej.game.controller;

import main.se.tevej.game.controller.input.base.CameraController;
import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.controller.input.base.libgdximplementation.InputLibgdxFactory;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.ViewManager;

public class ControllerManager {

    private ViewManager viewManager;
    private ModelManager modelManager;

    private TMouse mouse;
    private TKeyBoard keyBoard;

    private CameraController camera;

    public ControllerManager(ViewManager viewManager, ModelManager modelManager,
                             int worldWidth, int worldHeight,
                             OrderedInputMultiplexer inputMultiplexer) {
        this.viewManager = viewManager;
        this.modelManager = modelManager;
        initInput(inputMultiplexer);
        initControllers(worldWidth, worldHeight);
    }

    private void initInput(OrderedInputMultiplexer inputMultiplexer) {
        InputFactory inputFactory = new InputLibgdxFactory(inputMultiplexer);
        mouse = inputFactory.createMouse();
        keyBoard = inputFactory.createKeyBoard();
    }

    private void initControllers(int worldWidth, int worldHeight) {
        initCamera(worldWidth, worldHeight);
        initConstructor();
        initTime();
    }

    private void initCamera(int worldWidth, int worldHeight) {
        camera = new CameraController(
            viewManager, 0, 0, mouse, worldWidth, worldHeight);
    }

    private void initConstructor() {
        ConstructionController constructor = new ConstructionController(modelManager,
                modelManager.getWorldEntity(), camera, keyBoard, mouse,
                viewManager.getSelectedBuildingRenderer(), viewManager);

        viewManager.getBuildingGui().addSelectedListener(constructor);
    }

    private void initTime() {
        new TimeController(keyBoard);
    }

}
