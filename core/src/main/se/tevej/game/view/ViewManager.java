package main.se.tevej.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.controller.screen.ChangeScreen;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.gamerendering.SelectedBuildingRenderer;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.entity.EntityViewManager;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.BuildingInfoGui;
import main.se.tevej.game.view.gui.GameControlsGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.view.gui.base.GuiFactory;

public class ViewManager {

    private ModelManager modelManager;
    private TBatchRenderer batchRenderer;

    private EntityViewManager entityViewManager;
    private SelectedBuildingRenderer selectedRenderer;

    private InventoryGui inventoryGui;
    private BuildingGui buildingGui;
    private BuildingInfoGui buildingInfoGui;
    private GameControlsGui gameControlsGui;

    private float zoomMultiplier;

    // The current camera positions in world coordinates.
    private float currCameraPosX;
    private float currCameraPosY;

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final float minTilesPerScreen = 5;
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

    private void renderGui(float deltaTime) {
        inventoryGui.update(deltaTime);
        inventoryGui.render();
        buildingGui.update(deltaTime);
        buildingGui.render();
        gameControlsGui.update(deltaTime);
        gameControlsGui.render();
        buildingInfoGui.update(deltaTime);
        buildingInfoGui.render();
    }

    private void initGui(GuiFactory guiFactory, ChangeScreen screenChanger) {
        inventoryGui = new InventoryGui(guiFactory, modelManager.getInventoryEntity());
        buildingGui = new BuildingGui(guiFactory);
        gameControlsGui = new GameControlsGui(guiFactory, screenChanger);
        buildingInfoGui = new BuildingInfoGui(guiFactory);
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
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float newTilesPerWidth = screenWidth / (newMultiplier * pixelPerTile);
        float newTilesPerHeight = screenHeight / (newMultiplier * pixelPerTile);

        int worldWidth = modelManager.getWorldWidth();
        int worldHeight = modelManager.getWorldHeight();

        // Makes sure we keep within reasonable zoom levels.
        if (newTilesPerWidth < minTilesPerScreen || newTilesPerHeight < minTilesPerScreen) {
            float maxWidthZoomMp = screenWidth / (minTilesPerScreen * pixelPerTile);
            float maxHeightZoomMp = screenHeight / (minTilesPerScreen * pixelPerTile);
            zoomMultiplier = Math.min(maxWidthZoomMp, maxHeightZoomMp);
        } else if (newTilesPerWidth > worldWidth || newTilesPerHeight > worldHeight) {
            float minWidthZoomMp = screenWidth / (worldWidth * pixelPerTile);
            float minHeightZoomMp = screenHeight / (worldHeight * pixelPerTile);
            zoomMultiplier = Math.max(minWidthZoomMp, minHeightZoomMp);
        } else {
            zoomMultiplier = newMultiplier;
        }


    }

    public float getZoomMultiplier() {
        return zoomMultiplier;
    }

}
