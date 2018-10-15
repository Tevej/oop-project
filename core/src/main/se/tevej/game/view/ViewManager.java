package main.se.tevej.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import main.se.tevej.game.Manager;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.view.gui.BuildingGui;
import main.se.tevej.game.view.gui.InventoryGui;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.libgdx.RenderingLibgdxFactory;

public class ViewManager implements Manager {

    private ModelManager modelManager;
    private RenderingFactory renderingFactory;

    private EntityViewManager entityViewManager;

    private SelectedBuildingRenderer selectedRenderer;

    private InventoryGui inventoryGui;
    private BuildingGui buildingGui;

    public ViewManager(ModelManager modelManager) {
        this.modelManager = modelManager;
    }

    @Override
    public void update(float deltaTime) {
        clearScreen();

        entityViewManager.render();
        selectedRenderer.render();

        renderGui(deltaTime);
    }

    @Override
    public void init() {
        initRenderFactory();
        initGui();
        initRenders();
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

    private void renderGui(float deltaTime) {
        inventoryGui.update(deltaTime);
        inventoryGui.render();
        buildingGui.update(deltaTime);
        buildingGui.render();
    }


    private void initRenderFactory() {
        renderingFactory = new RenderingLibgdxFactory();
    }

    private void initGui() {
        inventoryGui = new InventoryGui(renderingFactory, modelManager.getInventoryEntity());
        buildingGui = new BuildingGui(renderingFactory);
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

}
