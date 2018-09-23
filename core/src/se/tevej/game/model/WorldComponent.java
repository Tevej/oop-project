package se.tevej.game.model;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.components.TileComponent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WorldComponent {
    private Entity[] tiles;
    private int width;
    private int height;

    public WorldComponent(int width, int height) {
        tiles = new Entity[width * height];
        this.width = width;
        this.height = height;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Create a tile with tilefactory.
                tiles[x + y * width] = null;
            }
        }
    }

    public Entity getTileAt(int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            return tiles[x + y * width];
        }
    }
}
