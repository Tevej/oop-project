package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class TileComponent implements Component {
    private Entity occupier;

    public TileComponent() {

    }

    public boolean isOccupied() {
        return occupier != null;
    }
}
