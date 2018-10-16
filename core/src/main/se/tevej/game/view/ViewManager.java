package main.se.tevej.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.gamerendering.SelectedBuildingRenderer;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.libgdximplementation.GameRenderingLibgdxFactory;
import main.se.tevej.game.view.gamerendering.entity.EntityViewManager;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.InputProcessorListener;
import main.se.tevej.game.view.gui.base.libgdximplementation.GuiLibgdxFactory;

public class ViewManager {

    private ModelManager modelManager;

    private GameRenderingFactory renderingFactory;
    private GuiFactory guiFactory;

    private TBatchRenderer batchRenderer;

    private EntityViewManager entityViewManager;
    private SelectedBuildingRenderer selectedRenderer;

    private InventoryGui inventoryGui;
    private BuildingGui buildingGui;

    //Min tiles for screen
    private static final float MIN_TILES = 5;
    private static final float PIXELS_PER_TILES = 32f;

    // The current camera positions in world coordinates.
    private float currCameraPosX;
    private float currCameraPosY;

    private float zoomMultiplier;

    public ViewManager(ModelManager modelManager, InputProcessorListener listener) {
        this.modelManager = modelManager;
        zoomMultiplier = 1f;
        initFactories(listener);
        initGui();
        initRenders();
    }

    public void update(float deltaTime) {
        clearScreen();
        renderGameRendering();
        renderGui(deltaTime);
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

    private void renderGameRendering() {
        batchRenderer.beginRendering();
        entityViewManager.render(
            batchRenderer, currCameraPosX, currCameraPosY, PIXELS_PER_TILES * zoomMultiplier
        );
        selectedRenderer.render(
            batchRenderer, PIXELS_PER_TILES * zoomMultiplier
        );
        batchRenderer.endRendering();
    }

    private void renderGui(float deltaTime) {
        inventoryGui.update(deltaTime);
        inventoryGui.render();
        buildingGui.update(deltaTime);
        buildingGui.render();
    }

    private void initFactories(InputProcessorListener listener) {
        guiFactory = new GuiLibgdxFactory(listener);
        renderingFactory = new GameRenderingLibgdxFactory();
    }

    private void initGui() {
        inventoryGui = new InventoryGui(guiFactory, modelManager.getInventoryEntity());
        buildingGui = new BuildingGui(guiFactory);
    }

    private void initRenders() {
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
        return PIXELS_PER_TILES;
    }

    // Number of pixels to zoom
    public void zoom(float newMultiplier) {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float newTilesPerWidth = screenWidth / (newMultiplier * PIXELS_PER_TILES);
        float newTilesPerHeight = screenHeight / (newMultiplier * PIXELS_PER_TILES);

        int worldWidth = modelManager.getWorldWidth();
        int worldHeight = modelManager.getWorldHeight();

        if (newTilesPerWidth < MIN_TILES || newTilesPerHeight < MIN_TILES) {
            float maxWidthZoomMp = screenWidth / (MIN_TILES * PIXELS_PER_TILES);
            float maxHeightZoomMp = screenHeight / (MIN_TILES * PIXELS_PER_TILES);
            zoomMultiplier = Math.min(maxWidthZoomMp, maxHeightZoomMp);
        } else if (newTilesPerWidth > worldWidth || newTilesPerHeight > worldHeight) {
            float minWidthZoomMp = screenWidth / (worldWidth * PIXELS_PER_TILES);
            float minHeightZoomMp = screenHeight / (worldHeight * PIXELS_PER_TILES);
            zoomMultiplier = Math.max(minWidthZoomMp, minHeightZoomMp);
        } else {
            zoomMultiplier = newMultiplier;
        }


    }

    public float getZoomMultiplier() {
        return zoomMultiplier;
    }

}
