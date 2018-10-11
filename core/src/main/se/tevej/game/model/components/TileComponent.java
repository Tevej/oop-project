package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class TileComponent implements Component {
    private Entity occupier;

    public Entity getOccupier() {
        return occupier;
    }

    public void occupy(Entity occupier) {
        this.occupier = occupier;
    }

    public boolean isOccupied() {
        return occupier != null;
    }
}
