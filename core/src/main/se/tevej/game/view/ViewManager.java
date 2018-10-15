package main.se.tevej.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.libgdximplementation.GameRenderingLibgdxFactory;
import main.se.tevej.game.view.gamerendering.entity.EntityViewManager;
import main.se.tevej.game.view.gamerendering.entity.SelectedBuildingRenderer;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.InputProcessorListener;
import main.se.tevej.game.view.gui.base.libgdximplementation.GuiLibgdxFactory;

public class ViewManager {

    private ModelManager modelManager;

    private GameRenderingFactory renderingFactory;
    private GuiFactory guiFactory;

    private EntityViewManager entityViewManager;

    private SelectedBuildingRenderer selectedRenderer;

    private InventoryGui inventoryGui;
    private BuildingGui buildingGui;

    private float pixelPerTile = 32f;

    public ViewManager(ModelManager modelManager, InputProcessorListener listener) {
        this.modelManager = modelManager;
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
        entityViewManager.setPosition(cameraPosX, cameraPosY);
    }

    public SelectedBuildingRenderer getSelectedBuildingRenderer() {
        return selectedRenderer;
    }

    public BuildingGui getBuildingGui() {
        return buildingGui;
    }

    private void renderGameRendering() {
        entityViewManager.render(pixelPerTile);
        selectedRenderer.render();
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
}
