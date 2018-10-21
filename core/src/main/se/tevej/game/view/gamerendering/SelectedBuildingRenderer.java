package main.se.tevej.game.view.gamerendering;

import java.util.HashMap;
import java.util.Map;

import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.TTexture;

public class SelectedBuildingRenderer implements OnBuildingSelectedToBuild {

    private final Map<BuildingType, TTexture> textureMap;

    private BuildingType selectedBuilding;
    private float positionX;
    private float positionY;

    public SelectedBuildingRenderer(GameRenderingFactory renderingFactory) {
        textureMap = new HashMap<>();
        textureMap.put(
            BuildingType.HOME,
            renderingFactory.createTexture("buildings/home.png")
        );

        textureMap.put(
            BuildingType.LUMBERMILL,
            renderingFactory.createTexture("buildings/lumbermill.png")
        );

        textureMap.put(
            BuildingType.QUARRY,
            renderingFactory.createTexture("buildings/quarry.png")
        );

        textureMap.put(
            BuildingType.PUMP,
            renderingFactory.createTexture("buildings/pump.png")
        );

        textureMap.put(
            BuildingType.FARM,
            renderingFactory.createTexture("buildings/farm.png")
        );

        textureMap.put(
            BuildingType.FARM_LAND,
            renderingFactory.createTexture("buildings/farm_land.png")
        );
    }

    public void render(TBatchRenderer batchRenderer, float pixelPerTile) {
        if (selectedBuilding != null && selectedBuilding != BuildingType.NONE) {
            TTexture texture = this.textureMap.get(selectedBuilding);
            batchRenderer.renderTexture(texture, positionX,
                positionY, pixelPerTile,
                pixelPerTile);
        }
    }

    public void setPosition(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void buildingSelectedToBuild(BuildingType selectedBuilding) {
        this.selectedBuilding =
            this.selectedBuilding == selectedBuilding
                ? null
                : selectedBuilding;
    }
}
