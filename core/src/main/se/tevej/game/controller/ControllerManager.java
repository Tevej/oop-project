package main.se.tevej.game.controller;

import main.se.tevej.game.controller.input.base.CameraController;
import main.se.tevej.game.controller.input.base.InputFactory;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.controller.input.base.libgdximplementation.InputLibgdxFactory;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.utils.Manager;
import main.se.tevej.game.utils.Options;
import main.se.tevej.game.view.ViewManager;

public class ControllerManager implements Manager {

    private ViewManager viewManager;
    private ModelManager modelManager;

    private TMouse mouse;
    private TKeyBoard keyBoard;

    private Options options;

    private CameraController camera;

    public ControllerManager(Options options, ViewManager viewManager, ModelManager modelManager) {
        this.options = options;
        this.viewManager = viewManager;
        this.modelManager = modelManager;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void init() {
        initInput();
        initControllers();
    }

    private void initInput() {
        InputFactory inputFactory = new InputLibgdxFactory();
        mouse = inputFactory.createMouse();
        keyBoard = inputFactory.createKeyBoard();
    }

    private void initControllers() {
        initCamera();
        initConstructor();
        initTime();
    }

    private void initCamera() {
        camera = new CameraController(
            viewManager, 0, 0, mouse, options);
    }

    private void initConstructor() {
        ConstructionController constructor = new ConstructionController(
            modelManager,
            modelManager.getWorldEntity(),
            camera,
            keyBoard,
            mouse,
            viewManager.getSelectedBuildingRenderer(),
            options
        );

        viewManager.getBuildingGui().addSelectedListener(constructor);
    }

    private void initTime() {
        new TimeController(keyBoard);
    }

}
