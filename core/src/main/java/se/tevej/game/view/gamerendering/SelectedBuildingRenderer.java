package main.java.se.tevej.game.view.gamerendering;

import java.util.HashMap;
import java.util.Map;

import main.java.se.tevej.game.model.components.buildings.BuildingType;
import main.java.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.java.se.tevej.game.view.gamerendering.base.TBatchRenderer;

/**
 * Renders the currently selected building to build. The building is selected using BuildingGui.
 */
public class SelectedBuildingRenderer implements OnBuildingSelectedToBuild {

    private static final Map<BuildingType, String> BUILDING_TO_IMAGE;

    static {
        BUILDING_TO_IMAGE = new HashMap<>();

        BUILDING_TO_IMAGE.put(BuildingType.HOME, "buildings/home.png");
        BUILDING_TO_IMAGE.put(BuildingType.LUMBERMILL, "buildings/lumbermill.png");
        BUILDING_TO_IMAGE.put(BuildingType.QUARRY, "buildings/quarry.png");
        BUILDING_TO_IMAGE.put(BuildingType.PUMP, "buildings/pump.png");
        BUILDING_TO_IMAGE.put(BuildingType.FARM, "buildings/farm.png");
        BUILDING_TO_IMAGE.put(BuildingType.FARM_LAND, "buildings/farm_land.png");
    }

    private BuildingType selectedBuilding;
    private float positionX;
    private float positionY;
    private GameRenderingFactory renderingFactory;

    public SelectedBuildingRenderer(GameRenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;
    }

    public void render(TBatchRenderer batchRenderer, float pixelPerTile) {
        if (selectedBuilding != null && selectedBuilding != BuildingType.NONE) {
            batchRenderer.renderTexture(
                this.renderingFactory.createTexture(BUILDING_TO_IMAGE.get(selectedBuilding)),
                positionX,
                positionY,
                pixelPerTile,
                pixelPerTile
            );
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
