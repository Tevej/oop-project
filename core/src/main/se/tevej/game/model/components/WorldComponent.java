package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class WorldComponent implements Component {
    private Entity[] tiles;
    private int width;
    private int height;

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "Dis is fine.")

    public WorldComponent(int width, int height, Entity[] tiles) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Entity getTileAt(int x, int y) {
        Entity tile = null;
        if (x >= 0 && y >= 0 && x < width && y < height) {
            tile = tiles[x + y * width];
        }
        return tile;
    }
}
