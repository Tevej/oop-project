package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gui.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

import java.util.HashMap;
import java.util.Map;

public class SelectedBuildingRenderer implements OnBuildingSelectedToBuild {

    private final Map<BuildingType, TTexture> buildingTypeToTexture;
    private final TBatchRenderer batchRenderer;

    private BuildingType selectedBuildingType;
    private float xPosition, yPosition;

    public SelectedBuildingRenderer(RenderingFactory renderingFactory) {
        buildingTypeToTexture = new HashMap<>();
        buildingTypeToTexture.put(BuildingType.HOME, renderingFactory.createTexture("buildings/home.jpg"));
        buildingTypeToTexture.put(BuildingType.LUMBERMILL, renderingFactory.createTexture("buildings/lumberMill.jpg"));
        buildingTypeToTexture.put(BuildingType.QUARRY, renderingFactory.createTexture("buildings/quarry.jpg"));
        buildingTypeToTexture.put(BuildingType.PUMP, renderingFactory.createTexture("buildings/pump.png"));

        batchRenderer = renderingFactory.createBatchRenderer();
    }

    public void render() {
        if(selectedBuildingType != null && selectedBuildingType != BuildingType.NONE){
            batchRenderer.beginRendering();
            batchRenderer.renderTexture(this.buildingTypeToTexture.get(selectedBuildingType), xPosition, yPosition);
            batchRenderer.endRendering();
        }
    }

    public void setPosition(float xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public void buildingSelectedToBuild(BuildingType selectedBuildingType) {
        this.selectedBuildingType = this.selectedBuildingType == selectedBuildingType ? null : selectedBuildingType;
    }
}
