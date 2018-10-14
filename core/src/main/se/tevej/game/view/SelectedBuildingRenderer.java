package main.se.tevej.game.view;

import java.util.HashMap;
import java.util.Map;

import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gui.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class SelectedBuildingRenderer implements OnBuildingSelectedToBuild {

    private final Map<BuildingType, TTexture> buildingTypeToTexture;
    private final TBatchRenderer batchRenderer;

    private BuildingType selectedBuildingType;
    private float positionX;
    private float positionY;

    public SelectedBuildingRenderer(RenderingFactory renderingFactory) {
        buildingTypeToTexture = new HashMap<>();
        buildingTypeToTexture.put(
            BuildingType.HOME,
            renderingFactory.createTexture("buildings/home.jpg")
        );

        buildingTypeToTexture.put(
            BuildingType.LUMBERMILL,
            renderingFactory.createTexture("buildings/lumberMill.jpg")
        );

        buildingTypeToTexture.put(
            BuildingType.QUARRY,
            renderingFactory.createTexture("buildings/quarry.jpg")
        );

        buildingTypeToTexture.put(
            BuildingType.PUMP,
            renderingFactory.createTexture("buildings/pump.png")
        );

        batchRenderer = renderingFactory.createBatchRenderer();
    }

    public void render() {
        if (selectedBuildingType != null && selectedBuildingType != BuildingType.NONE) {
            batchRenderer.beginRendering();
            batchRenderer.renderTexture(
                this.buildingTypeToTexture.get(selectedBuildingType),
                positionX,
                positionY
            );
            batchRenderer.endRendering();
        }
    }

    public void setPosition(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void buildingSelectedToBuild(BuildingType selectedBuildingType) {
        this.selectedBuildingType =
            this.selectedBuildingType == selectedBuildingType
                ? null
                : selectedBuildingType;
    }
}
