package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;

/**
 * A entity with this component can be identified as a building with the type this class holds.
 */
public class BuildingComponent implements Component {
    private BuildingType type;

    public BuildingComponent(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
