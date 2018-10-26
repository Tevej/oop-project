package main.java.se.tevej.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.java.se.tevej.game.model.ModelManager;
import main.java.se.tevej.game.view.gamerendering.SelectedBuildingRenderer;
import main.java.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.java.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.java.se.tevej.game.view.gamerendering.entity.EntityViewManager;
import main.java.se.tevej.game.view.gui.BuildingGui;
import main.java.se.tevej.game.view.gui.BuildingInfoGui;
import main.java.se.tevej.game.view.gui.ChangeScreen;
import main.java.se.tevej.game.view.gui.GameControlsGui;
import main.java.se.tevej.game.view.gui.InventoryGui;
import main.java.se.tevej.game.view.gui.base.GuiFactory;
import main.java.se.tevej.game.view.gui.time.RegisterTimeController;
import main.java.se.tevej.game.view.gui.time.SetTimeMultiplier;
import main.java.se.tevej.game.view.gui.time.TimeControlGui;

/**
 * The view of the game. Manages the game rendering as well as the gui updating and rendering.
 */
public class ViewManager {

    private ModelManager modelManager;
    private TBatchRenderer batchRenderer;

    private EntityViewManager entityViewManager;
    private SelectedBuildingRenderer selectedRenderer;

    private InventoryGui inventoryGui;
    private BuildingGui buildingGui;
    private BuildingInfoGui buildingInfoGui;
    private GameControlsGui gameControlsGui;
    private TimeControlGui timeControlGui;

    private float zoomMultiplier;

    // The current camera positions in world coordinates.
    private float currCameraPosX;
    private float currCameraPosY;

    private float maxZoom;
    private float minZoom;

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final float pixelPerTile = 32f;

    public ViewManager(ModelManager modelManager, GameRenderingFactory renderingFactory,
                       GuiFactory guiFactory, ChangeScreen screenChanger) {
        this.modelManager = modelManager;

        zoomMultiplier = 1f;
        initGui(guiFactory, screenChanger);
        initRenders(renderingFactory);
        calcMinMaxZoom(modelManager);
    }

    public void update(float deltaTime) {
        clearScreen();
        renderGameRendering();
        updateGui(deltaTime);
        renderGui();
    }

    public void setPosition(float cameraPosX, float cameraPosY) {
        this.currCameraPosX = cameraPosX;
        this.currCameraPosY = cameraPosY;
    }

    public SelectedBuildingRenderer getSelectedBuildingRenderer() {
        return selectedRenderer;
    }

    public BuildingGui getBuildingGui() {
        return buildingGui;
    }

    public BuildingInfoGui getBuildingInfoGui() {
        return buildingInfoGui;
    }

    private void renderGameRendering() {
        batchRenderer.beginRendering();
        entityViewManager.render(
            batchRenderer, currCameraPosX, currCameraPosY, pixelPerTile * zoomMultiplier
        );
        selectedRenderer.render(
            batchRenderer, pixelPerTile * zoomMultiplier
        );
        batchRenderer.endRendering();
    }

    private void renderGui() {
        inventoryGui.render();
        buildingGui.render();
        gameControlsGui.render();
        buildingInfoGui.render();
        timeControlGui.render();
    }

    private void updateGui(float deltaTime) {
        inventoryGui.update(deltaTime);
        buildingGui.update(deltaTime);
        gameControlsGui.update(deltaTime);
        buildingInfoGui.update(deltaTime);
        timeControlGui.update(deltaTime);
        timeControlGui.render();
    }

    private void initGui(GuiFactory guiFactory, ChangeScreen screenChanger) {
        inventoryGui = new InventoryGui(guiFactory, modelManager.getInventoryEntity());
        buildingGui = new BuildingGui(guiFactory);
        gameControlsGui = new GameControlsGui(guiFactory, screenChanger);
        buildingInfoGui = new BuildingInfoGui(guiFactory);
        timeControlGui = new TimeControlGui(guiFactory);
    }

    private void initRenders(GameRenderingFactory renderingFactory) {
        batchRenderer = renderingFactory.createBatchRenderer();

        entityViewManager = new EntityViewManager(modelManager, renderingFactory);
        selectedRenderer = new SelectedBuildingRenderer(renderingFactory);
        buildingGui.addSelectedListener(selectedRenderer);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public float getPixelPerTile() {
        return pixelPerTile;
    }

    // Number of pixels to zoom

    public void zoom(float newMultiplier) {
        zoomMultiplier = limitZoom(newMultiplier);
    }

    public float getZoomMultiplier() {
        return zoomMultiplier;
    }

    private float limitZoom(float newZoom) {
        float result = newZoom;
        result = Math.max(minZoom, result);
        result = Math.min(maxZoom, result);
        return result;
    }

    private void calcMinMaxZoom(ModelManager modelManager) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        int worldWidth = modelManager.getWorldWidth();
        int worldHeight = modelManager.getWorldHeight();

        float minWidthZoomMp = screenWidth / (worldWidth * pixelPerTile);
        float minHeightZoomMp = screenHeight / (worldHeight * pixelPerTile);

        this.minZoom = Math.max(minWidthZoomMp, minHeightZoomMp);

        float minTilesPerScreen = 5;
        float maxWidthZoomMp = screenWidth / (minTilesPerScreen * pixelPerTile);
        float maxHeightZoomMp = screenHeight / (minTilesPerScreen * pixelPerTile);

        this.maxZoom = Math.min(maxWidthZoomMp, maxHeightZoomMp);
    }

    public void setTimeControllers(RegisterTimeController registerTime,
                                   SetTimeMultiplier setTimeMultiplier) {
        registerTime.registerTimeController(timeControlGui);
        timeControlGui.setSetTimeMultiplier(setTimeMultiplier);
    }

}
