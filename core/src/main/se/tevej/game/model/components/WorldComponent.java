package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.LinkedList;
import java.util.List;

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

    // Returns an array with 4/8 (depending on includeDiagonal)
    // elements that can be either null or a tileEntity that neighbours
    // the given entity. The order of the neighbours will be:
    // up, right, down, left, topRight, bottomRight, bottomLeft, topLeft
    public List<Entity> getTileNeighbours(Entity entity, boolean includeDiagonal) {
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        List<Entity> neighbours;
        if (posC == null) {
            System.out.println("getTileNeighbours was given an entity without a position!");
            neighbours = null;
        } else {
            neighbours = getNeighboursForTile(posC, includeDiagonal);
        }
        return neighbours;
    }

    // Returns a list of entities that are directly neighbouring a positionComponent.
    private List<Entity> getNeighboursForTile(PositionComponent posC, boolean includeDiagonal) {
        List<Entity> neighbours = new LinkedList<>();
        int x = posC.getX();
        int y = posC.getY();

        if (!isTileOccupied(x, y + 1)) neighbours.add(getTileAt(x, y + 1));
        if (!isTileOccupied(x + 1, y)) neighbours.add(getTileAt(x + 1, y));
        if (!isTileOccupied(x, y - 1)) neighbours.add(getTileAt(x, y - 1));
        if (!isTileOccupied(x, y + 1)) neighbours.add(getTileAt(x -1 , y));

        if (includeDiagonal) {
            if (!isTileOccupied(x + 1, y + 1)) neighbours.add(getTileAt(x + 1, y + 1));
            if (!isTileOccupied(x + 1, y - 1)) neighbours.add(getTileAt(x + 1, y - 1));
            if (!isTileOccupied(x - 1, y - 1)) neighbours.add(getTileAt(x - 1, y - 1));
            if (!isTileOccupied(x - 1, y + 1)) neighbours.add(getTileAt(x - 1, y + 1));
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
