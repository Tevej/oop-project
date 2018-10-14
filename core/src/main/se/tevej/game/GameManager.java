package main.se.tevej.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.controller.input.CameraController;
import main.se.tevej.game.controller.input.ConstructionController;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.TimeController;
import main.se.tevej.game.controller.input.libgdx.InputLibgdxFactory;
import main.se.tevej.game.controller.input.listeners.OnTimeChangeListener;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.entities.InventoryEntity;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.view.SelectedBuildingRenderer;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.libgdx.RenderingLibgdxFactory;

public class GameManager extends ApplicationAdapter implements OnTimeChangeListener {
    private EntityManager entityManager;
    private ViewManager view;
    private InventoryGui gui;
    private BuildingGui buildingGui;
    private SelectedBuildingRenderer buildingRenderer;

    private long lastFrameNanoTime;
    private float deltaTime;
    private float printFrameRate;

    // The current timeMultiplier (0 means pause, 1 means default speed etc...)
    private float timeMultiplier = 1f;

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "Fuckoff"
    )
    private final float fpsPrintDelay = 0.1f;

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final long billion = 1000 * 1000 * 1000;


    public GameManager() {
        super();
        calculateDeltaTime();
    }

    @Override
    public void create() {
        RenderingFactory renderingFactory = new RenderingLibgdxFactory();
        lastFrameNanoTime = System.nanoTime();

        entityManager = new EntityManager();
        view = new ViewManager(entityManager, renderingFactory);


        int worldWidth = 100;
        int worldHeight = 100;

        // Look over naming of method / implementation (also adds the world to the engine.)
        Entity worldEntity = new WorldEntity(worldWidth, worldHeight, entityManager);
        Entity inventoryEntity = new InventoryEntity();

        entityManager.addEntityToEngine(inventoryEntity);
        entityManager.addEntityToEngine(worldEntity);

        Entity buildHomeBuilding = new Entity();
        buildHomeBuilding.add(new BuildingComponent(BuildingType.HOME));

        Entity tile = worldEntity.getComponent(WorldComponent.class).getTileAt(5, 5);
        buildHomeBuilding.add(tile.getComponent(PositionComponent.class));
        buildHomeBuilding.add(worldEntity.getComponent(WorldComponent.class));
        buildHomeBuilding.add(new SignalComponent(SignalType.BUILDBUILDING));
        entityManager.getSignal().dispatch(buildHomeBuilding);

        buildingRenderer = new SelectedBuildingRenderer(renderingFactory);

        gui = new InventoryGui(renderingFactory, inventoryEntity);
        buildingGui = new BuildingGui(renderingFactory);
        buildingGui.addSelectedListener(buildingRenderer);

        InputLibgdxFactory inputFactory = new InputLibgdxFactory();
        TMouse mouse = inputFactory.createMouse();
        TKeyBoard keyBoard = inputFactory.createKeyBoard();
        CameraController camera = new CameraController(
            view, 0, 0, worldWidth, worldHeight, mouse);

        ConstructionController constructor = new ConstructionController(
            entityManager,
            worldEntity,
            camera,
            keyBoard,
            mouse,
            buildingRenderer
        );
        buildingGui.addSelectedListener(constructor);

        TimeController timeController = new TimeController(keyBoard);
        timeController.registerOnTimeChange(this);
    }

    @Override
    public void render() {
        calculateDeltaTime();

        entityManager.update(deltaTime);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        view.render();

        gui.update(deltaTime);
        gui.render();

        buildingGui.update(deltaTime);
        buildingGui.render();

        buildingRenderer.render();
    }

    private void calculateDeltaTime() {
        long currFrameNanoTime = System.nanoTime();
        long diff = currFrameNanoTime - lastFrameNanoTime;
        lastFrameNanoTime = currFrameNanoTime;
        deltaTime = (float) diff / (float) billion;

        printFrameRate += deltaTime;
        if (printFrameRate >= fpsPrintDelay) {
            System.out.println("FPS: " + (1 / deltaTime));
            printFrameRate = 0;
        }

        deltaTime *= timeMultiplier;
    }

    @Override
    public void updateTimeMultipler(float newMultiplier) {
        timeMultiplier = newMultiplier;
        System.out.println("TimeMultiplier updated to " + timeMultiplier);
    }

    @Override
    public void dispose() {
    }
}
