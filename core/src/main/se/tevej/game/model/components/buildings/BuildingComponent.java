package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;

public class BuildingComponent implements Component {
    private BuildingType type;

    public BuildingComponent(BuildingType type) {
        this.type = type;
    }

    public BuildingType getType() {
        return type;
    }
}
