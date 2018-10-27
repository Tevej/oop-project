package main.se.tevej.game.view.gamerendering;

import main.se.tevej.game.model.components.buildings.BuildingType;

/**
 * A function that gets called when a new building has been selected in BuildingGui, or
 * if no building is selected.
 */
public interface OnBuildingSelectedToBuild {

    void buildingSelectedToBuild(BuildingType buildingType);

}
