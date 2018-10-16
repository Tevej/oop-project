package main.se.tevej.game.model.components;

import java.util.List;

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

    // Returns an array with 8 elements that can be either null or a tileEntity that neighbours
    // the given entity. The order of the neighbours will be:
    // up, right, down, left, topRight, bottomRight, bottomLeft, topLeft
    public Entity[] getTileNeighbours(Entity entity, boolean includeDiagonal) {
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        Entity[] neighbours;
        if (posC != null) {
            neighbours = getNeighboursForTile(posC, includeDiagonal);
        } else {
            System.out.println("getTileNeighbours was given an entity without a position!");
            neighbours = null;
        }
        return neighbours;
    }

    // Returns a list of entities that are directly neighbouring a positionComponent.
    private Entity[] getNeighboursForTile(PositionComponent posC, boolean includeDiagonal) {
        Entity[] neighbours = includeDiagonal ? new Entity[8] : new Entity[4];
        int x = posC.getX();
        int y = posC.getY();
        neighbours[0] = getTileAt(x, y + 1);
        neighbours[1] = getTileAt(x + 1, y);
        neighbours[2] = getTileAt(x, y - 1);
        neighbours[3] = getTileAt(x, y + 1);

        if (includeDiagonal) {
            neighbours[4] = getTileAt(x + 1, y + 1);
            neighbours[5] = getTileAt(x + 1, y - 1);
            neighbours[6] = getTileAt(x - 1, y - 1);
            neighbours[7] = getTileAt(x - 1, y + 1);
        }
        return neighbours;
    }
}
