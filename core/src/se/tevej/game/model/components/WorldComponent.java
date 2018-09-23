package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.components.TileComponent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WorldComponent implements Component {
    private Entity[] tiles;
    private int width;
    private int height;

    public WorldComponent(int width, int height, Entity[] tiles) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
    }

    public Entity getTileAt(int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            return tiles[x + y * width];
        }
        return null;
    }
}
