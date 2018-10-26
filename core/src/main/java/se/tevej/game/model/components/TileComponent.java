package main.java.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * The world is made up of tiles and thier sole purpose is to hold an entity.
 * That is the behaviour a tile component adds to an entity.
 */
public class TileComponent implements Component {
    private Entity occupier;

    public TileComponent() {
        super();
    }

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
