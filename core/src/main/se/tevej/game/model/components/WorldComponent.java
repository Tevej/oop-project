package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * The world component is the world. it has all the tiles and by extension everything on the tiles.
 * Therefore, almost every interaction with the tiles in the world goes through this class.
 */
public class WorldComponent implements Component {
    private Entity[] tiles;
    private int width;
    private int height;

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "We dont want to have the ModelManager as a dependency/parameter.")
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

    // Returns an array with 4/8 (depending on includeDiagonal)
    // elements that can be either null or a tileEntity that neighbours
    // the given entity. The order of the neighbours will be:
    // up, right, down, left, topRight, bottomRight, bottomLeft, topLeft
    public Entity[] getTileNeighbours(Entity entity, boolean includeDiagonal) {
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        Entity[] neighbours;
        if (posC == null) {
            System.out.println("getTileNeighbours was given an entity without a position!");
            neighbours = null;
        } else {
            neighbours = getNeighboursForTile(posC, includeDiagonal);
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
        neighbours[3] = getTileAt(x - 1, y);

        if (includeDiagonal) {
            neighbours[4] = getTileAt(x + 1, y + 1);
            neighbours[5] = getTileAt(x + 1, y - 1);
            neighbours[6] = getTileAt(x - 1, y - 1);
            neighbours[7] = getTileAt(x - 1, y + 1);
        }
        return neighbours;
    }


    public void occupyTile(int worldX, int worldY, Entity entity) {
        TileComponent tileC = getTileAt(worldX, worldY).getComponent(TileComponent.class);
        tileC.occupy(entity);
    }

    public boolean isTileOccupied(int worldX, int worldY) {
        TileComponent tileC = getTileAt(worldX, worldY).getComponent(TileComponent.class);
        return tileC.isOccupied();
    }

    public void removeOccupier(int worldX, int worldY) {
        getTileAt(worldX, worldY).getComponent(TileComponent.class).occupy(null);
    }

    public Entity getTileOccupier(int worldX, int worldY) {
        return getTileAt(worldX, worldY).getComponent(TileComponent.class).getOccupier();
    }

}
