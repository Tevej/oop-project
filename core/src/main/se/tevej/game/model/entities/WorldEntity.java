package main.se.tevej.game.model.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class WorldEntity extends Entity {

    private double clusterSize = 0.7;
    private double clusterDilution = 0.5;
    private double waterSpawnProb = 0.006;
    private double woodSpawnProb = 0.004;
    private double stoneSpawnProb = 0.003;
    private int waterAmount = 1000;
    private int woodAmount = 1000;
    private int stoneAmount = 1000;

    public WorldEntity(int width, int height, ModelManager em) {
        super();
        Entity[] tiles = new Entity[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity tile = new TileEntity(x, y);
                tiles[x + y * width] = tile;
                em.addEntityToEngine(tile);
            }
        }
        WorldComponent worldComponent = new WorldComponent(width, height, tiles);
        generateNaturalResources(width, height, worldComponent, em);
        this.add(worldComponent);
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

    private void generateNaturalResources(int width, int height,
                                          WorldComponent world, ModelManager em) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PositionComponent pos = new PositionComponent(x, y);
                List<Entity> clusters = generateRandomClusters(pos, new ArrayList<>(), world, em);
                occupyTilesInCluster(clusters, world);
            }
        }
    }

    private List<Entity> generateRandomClusters(PositionComponent startPos,
                                                List<PositionComponent> occupiedSpots,
                                                WorldComponent world, ModelManager em) {
        double prob = clusterSize;
        double n = Math.random();
        Resource resource = null;
        List<PositionComponent> locations = new ArrayList<>();
        List<Entity> nrelist = new ArrayList<>();
        if (n < waterSpawnProb) {
            locations = generateCluster(prob, startPos, world, occupiedSpots);
            resource = new Resource(waterAmount, ResourceType.WATER);
        } else if (n < woodSpawnProb + waterSpawnProb) {
            locations = generateCluster(prob, startPos, world, occupiedSpots);
            resource = new Resource(woodAmount, ResourceType.WOOD);
        } else if (n < stoneSpawnProb + woodSpawnProb + waterSpawnProb) {
            locations = generateCluster(prob, startPos, world, occupiedSpots);
            resource = new Resource(stoneAmount, ResourceType.STONE);
        }
        for (PositionComponent loc : locations) {
            Entity resourceEntity =
                new NaturalResourceEntity(loc.getX(), loc.getY(), resource);
            nrelist.add(resourceEntity);
            em.addEntityToEngine(resourceEntity);
        }
        return nrelist;
    }

    private List<PositionComponent> generateCluster(double prob, PositionComponent startPos,
                                                    WorldComponent world,
                                                    List<PositionComponent> occupiedSpots) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                PositionComponent pos =
                    new PositionComponent(startPos.getX() + i, startPos.getY() + j);
                if (isOutOfBounds(pos, world.getWidth(), world.getHeight())) {
                    continue;
                }
                TileComponent newTile = world.getTileAt(pos.getX(), pos.getY())
                    .getComponent(TileComponent.class);
                if (Math.random() < prob && !occupiedSpots.contains(pos) && !newTile.isOccupied()) {
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