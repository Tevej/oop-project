package main.java.se.tevej.game.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Entity;

import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.TileComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.resources.ResourceType;

/**
 * The world entity creates our world. Upon startup, based on user input, it either restores the
 * previous world or generates a new.
 */
public class WorldEntity extends Entity {

    private final int width;
    private final int height;
    private final AddToEngineListener engineListener;

    public WorldEntity(int width, int height, AddToEngineListener listener) {
        super();
        Entity[] tiles = new Entity[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity tile = new TileEntity(x, y);
                tiles[x + y * width] = tile;
                listener.addEntityToEngine(tile);
            }
        }
        this.width = width;
        this.height = height;
        this.engineListener = listener;
        add(new WorldComponent(width, height, tiles));
    }

    public void createNewWorld() {
        generateNaturalResources();
    }

    public void createWorldFromSave(List<Entity> tileOccupiers) {
        WorldComponent worldC = getComponent(WorldComponent.class);
        PositionComponent posC;
        for (Entity occupier : tileOccupiers) {
            posC = occupier.getComponent(PositionComponent.class);
            worldC.occupyTile(posC.getX(), posC.getY(), occupier);
        }
    }

    private void occupyTilesInCluster(List<Entity> clusters, WorldComponent wc) {
        for (Entity entity : clusters) {
            PositionComponent pc = entity.getComponent(PositionComponent.class);
            Entity tc = wc.getTileAt(pc.getX(), pc.getY());
            if (tc != null) {
                tc.getComponent(TileComponent.class).occupy(entity);
            }
        }
    }

    private void generateNaturalResources() {
        WorldComponent world = getComponent(WorldComponent.class);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PositionComponent pos = new PositionComponent(x, y);
                List<Entity> clusters = generateRandomClusters(
                    pos, new ArrayList<>(), world, engineListener
                );
                occupyTilesInCluster(clusters, world);
            }
        }
    }



    private List<Entity> generateRandomClusters(PositionComponent startPos,
                                                List<PositionComponent> occupiedSpots,
                                                WorldComponent world,
                                                AddToEngineListener listener) {
        double waterClusterSize = 2000;
        double woodClusterSize = 1;
        double stoneClusterSize = 1;
        double waterSpawnProb = 0.001;
        double woodSpawnProb = 0.004;
        double stoneSpawnProb = 0.003;
        //Water having a negative amount indicates that it has an infinite amount
        int waterAmount = -1;
        int woodAmount = 1000;
        int stoneAmount = 1000;

        double n = Math.random();
        Resource resource = null;
        List<PositionComponent> locations = new ArrayList<>();
        List<Entity> natResEntityList = new ArrayList<>();
        if (n < waterSpawnProb) {
            locations = generateCluster(waterClusterSize, startPos, world, occupiedSpots);
            resource = new Resource(waterAmount, ResourceType.WATER);
        } else if (n < woodSpawnProb + waterSpawnProb) {
            locations = generateCluster(woodClusterSize, startPos, world, occupiedSpots);
            resource = new Resource(woodAmount, ResourceType.WOOD);
        } else if (n < stoneSpawnProb + woodSpawnProb + waterSpawnProb) {
            locations = generateCluster(stoneClusterSize, startPos, world, occupiedSpots);
            resource = new Resource(stoneAmount, ResourceType.STONE);
        }
        for (PositionComponent loc : locations) {
            Entity resourceEntity =
                new NaturalResourceEntity(loc.getX(), loc.getY(), resource);
            natResEntityList.add(resourceEntity);
            listener.addEntityToEngine(resourceEntity);
        }
        return natResEntityList;
    }

    private List<PositionComponent> generateCluster(double prob, PositionComponent startPos,
                                                    WorldComponent world,
                                                    List<PositionComponent> occupiedSpots) {
        double clusterDilution = 0.5;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                PositionComponent pos =
                    new PositionComponent(startPos.getX() + i, startPos.getY() + j);
                if (isOutOfBounds(pos, world.getWidth(), world.getHeight())) {
                    continue;
                }

                if (Math.random() < prob && !occupiedSpots.contains(pos)
                    && !world.isTileOccupied(pos.getX(), pos.getY())) {
                    occupiedSpots.add(pos);
                    generateCluster(prob * clusterDilution, pos, world, occupiedSpots);
                }

            }
        }
        return occupiedSpots;
    }

    private boolean isOutOfBounds(PositionComponent pos, int width, int height) {
        return pos.getX() < 0
            || pos.getY() < 0
            || pos.getX() >= width
            || pos.getY() >= height;
    }
}