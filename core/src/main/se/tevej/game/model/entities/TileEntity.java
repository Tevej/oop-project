package main.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.TileComponent;

public class TileEntity extends Entity {
    public TileEntity(int x, int y) {
        super();
        this.add(new TileComponent());
        this.add(new PositionComponent(x, y));
        this.add(new SizeComponent(1, 1));
    }
}
