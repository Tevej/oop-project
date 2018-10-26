package main.java.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.SizeComponent;
import main.java.se.tevej.game.model.components.TileComponent;

public class TileEntity extends Entity {
    public TileEntity(int x, int y) {
        super();
        this.add(new TileComponent());
        this.add(new PositionComponent(x, y));
        this.add(new SizeComponent(1, 1));
    }
}
