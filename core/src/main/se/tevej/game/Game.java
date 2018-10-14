package main.se.tevej.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import main.se.tevej.game.controller.input.*;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTimeChangeListener;
import main.se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.factories.WorldFactory;
import main.se.tevej.game.view.SelectedBuildingRenderer;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gui.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.rendering.ui.*;
import main.se.tevej.game.view.View;
import main.se.tevej.game.view.rendering.RenderingFactory;

public class Game extends ApplicationAdapter implements OnTimeChangeListener {
    private TKeyBoard keyboard;
    private TMouse mouse;

    private RenderingFactory renderingFactory;

    private EntityManager em;
    private View view;
    private TTable table;
    private InputLibgdxFactory inputLibgdxFactory;

    private InventoryGui gui;
    private BuildingGui buildingGui;
    private SelectedBuildingRenderer selectedBuildingRenderer;
    private CameraController cameraController;

    private long lastFrameNanoTime;
    private long currFrameNanoTime;
    private float deltaTime;
    private float printFrameRate;

    // The current timeMultiplier (0 means pause, 1 means default speed etc...)
    private float timeMultiplier = 1f;

    private final static long BILLION = (1000 * 1000 * 1000);

    @Override
    public void create() {
        inputLibgdxFactory = new InputLibgdxFactory();
        renderingFactory = new RenderingLibgdxFactory();
        lastFrameNanoTime = System.nanoTime();

        mouse = inputLibgdxFactory.createMouse();
        keyboard = inputLibgdxFactory.createKeyBoard();

        em = new EntityManager();
        view = new View(em, renderingFactory);

        int worldWidth = 100;
        int worldHeight = 100;
        cameraController = new CameraController(view, inputLibgdxFactory, 0, 0, worldWidth, worldHeight, mouse);


		TButton button = renderingFactory.createButton().image("hulk.jpeg").addListener((key) -> System.out.println("Hej!"));
		TSelectableList selectableList = renderingFactory.createSelectableList().items("Glass", "Godis", "Dricka", "Choklad", "Asdf", "Hmmm", "Marabou").addListener(newSelected -> System.out.println("Selected: " + newSelected));

        TTextField textField = renderingFactory.createTextField().set("Hej").addListener(value -> {
            System.out.println("New value of textfield:" + value);
        });

        TLabel label = renderingFactory.createLabel().text("This is a label");

        table = renderingFactory.createTable().x((Gdx.graphics.getWidth() / 2f)).y(Gdx.graphics.getHeight() - 200).grid(2, 2).debug(true);

        table.addElement(button).width(200).height(50);
        table.addElement(textField).width(200).height(50);
        table.addElement(label).width(200).height(200);
        table.addElement(selectableList).width(200).height(200);

		// Look over naming of method / implementation (also adds the world to the engine.)
		Entity worldEntity = WorldFactory.createWorldEntity(worldWidth, worldHeight, em);
		Entity inventoryEntity = new Entity();
		InventoryComponent iC = new InventoryComponent();
		inventoryEntity.add(iC);
		iC.addResource(new Resource(1000, ResourceType.WOOD));
        iC.addResource(new Resource(1000, ResourceType.WATER));
        iC.addResource(new Resource(1000, ResourceType.STONE));

		em.addEntityToEngine(inventoryEntity);
		em.addEntityToEngine(worldEntity);

		Entity buildHomeBuilding = new Entity();
		buildHomeBuilding.add(new BuildingComponent(BuildingType.HOME));
		buildHomeBuilding.add(worldEntity.getComponent(WorldComponent.class).getTileAt(5, 5).getComponent(PositionComponent.class));
		buildHomeBuilding.add(worldEntity.getComponent(WorldComponent.class));
		buildHomeBuilding.add(new SignalComponent(SignalType.BUILDBUILDING));
		em.getSignal().dispatch(buildHomeBuilding);

        selectedBuildingRenderer = new SelectedBuildingRenderer(renderingFactory);

        ConstructionController constructionController = new ConstructionController(em, inputLibgdxFactory, worldEntity, cameraController, keyboard, mouse, selectedBuildingRenderer);
        gui = new InventoryGui(renderingFactory, inventoryEntity);
        buildingGui = new BuildingGui(renderingFactory);
        buildingGui.addSelectedListener(constructionController);
        buildingGui.addSelectedListener(selectedBuildingRenderer);

        TimeController timeController = new TimeController(keyboard);
        timeController.registerOnTimeChange(this);
    }

    @Override
    public void render() {
        calculateDeltaTime();

        em.update(deltaTime);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        view.render();

        table.update(deltaTime);
        //table.render();
        gui.update(deltaTime);
        gui.render();

        buildingGui.update(deltaTime);
        buildingGui.render();

        selectedBuildingRenderer.render();
    }

    private void calculateDeltaTime() {
        currFrameNanoTime = System.nanoTime();
        long diff = currFrameNanoTime - lastFrameNanoTime;
        lastFrameNanoTime = currFrameNanoTime;
        deltaTime = ((float)diff / (float)BILLION);

        printFrameRate += deltaTime;
        if (printFrameRate >= 0.1f) {
            //System.out.println("FPS: " + (1 / deltaTime));
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
