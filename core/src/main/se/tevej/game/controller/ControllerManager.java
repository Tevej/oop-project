package main.se.tevej.game.controller;

import main.se.tevej.game.controller.input.base.CameraController;
import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.controller.time.TimeController;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gui.time.OnTimeChangeListener;

/**
 * This is the central hub for all controllers and the one place to manage and initialize them.
 */
public class ControllerManager {

    private ViewManager viewManager;
    private ModelManager modelManager;

    private TMouse mouse;
    private TKeyBoard keyBoard;

    private CameraController camera;

    public ControllerManager(ViewManager viewManager, ModelManager modelManager,
                             int worldWidth, int worldHeight,
                             InputFactory inputFactory,
                             OnTimeChangeListener timeListener) {
        this.viewManager = viewManager;
        this.modelManager = modelManager;
        initInput(inputFactory);
        initControllers(worldWidth, worldHeight, timeListener);
    }

    private void initInput(InputFactory inputFactory) {
        mouse = inputFactory.createMouse();
        keyBoard = inputFactory.createKeyBoard();
    }

    private void initControllers(int worldWidth, int worldHeight, OnTimeChangeListener listener) {
        initCamera(worldWidth, worldHeight);
        initConstructor();
        initTime(listener);
        initInfo();
    }

    private void initCamera(int worldWidth, int worldHeight) {
        camera = new CameraController(
            viewManager, 0, 0, mouse, keyBoard, worldWidth, worldHeight);
    }

    private void initConstructor() {
        ConstructionController constructor = new ConstructionController(modelManager,
            modelManager.getWorldEntity(), camera, keyBoard, mouse,
            viewManager.getSelectedBuildingRenderer(), viewManager);

        viewManager.getBuildingGui().addSelectedListener(constructor);
    }

    private void initTime(OnTimeChangeListener listener) {
        TimeController timeController = new TimeController(keyBoard);
        timeController.registerOnTimeChange(listener);
        viewManager.setTimeControllers(timeController, timeController);
    }

    private void initInfo() {
        InfoController infoController = new InfoController(
            modelManager.getWorldEntity().getComponent(WorldComponent.class),
            camera,
            viewManager.getBuildingInfoGui(),
            mouse);
        viewManager.getBuildingGui().addSelectedListener(infoController);
    }
}
