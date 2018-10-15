package main.se.tevej.game.controller;

import main.se.tevej.game.Manager;
import main.se.tevej.game.Options;
import main.se.tevej.game.controller.input.CameraController;
import main.se.tevej.game.controller.input.InputFactory;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.TimeController;
import main.se.tevej.game.controller.input.libgdx.InputLibgdxFactory;
import main.se.tevej.game.model.ashley.ModelManager;
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
            viewManager, 0, 0, options.getWorldWidth(), options.getWorldHeight(), mouse);
    }

    private void initConstructor() {
        ConstructionController constructor = new ConstructionController(
            modelManager,
            modelManager.getWorldEntity(),
            camera,
            keyBoard,
            mouse,
            viewManager.getSelectedBuildingRenderer()
        );

        viewManager.getBuildingGui().addSelectedListener(constructor);
    }

    private void initTime() {
        new TimeController(keyBoard);
    }

}
