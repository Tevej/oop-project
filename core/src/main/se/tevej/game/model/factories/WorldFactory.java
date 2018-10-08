package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.*;
import main.se.tevej.game.model.resource.Resource;
import main.se.tevej.game.model.resource.ResourceType;

import java.util.*;

public class WorldFactory {
    public static Entity createWorldEntity(int width, int height, EntityManager em) {
        Entity[] tiles = new Entity[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity tile = createTileEntity(x, y, em);
                tiles[x + y * width] = tile;
                em.addEntityToEngine(tile);
            }
        }
        Entity worldEntity = em.createEntity();
        WorldComponent wc = new WorldComponent(width, height, tiles);
        worldEntity.add(wc);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                PositionComponent pos = new PositionComponent(x,y);
                List<Entity> clusters = generateRandomClusters(0.7, pos, new ArrayList<>(), em);
                occupyTiles(clusters, wc);
            }
        }

        worldEntity.add(new InventoryComponent());
        return worldEntity;
    }

    private static void occupyTiles(List<Entity> clusters, WorldComponent wc){
        for (Entity entity : clusters) {
            PositionComponent pc = entity.getComponent(PositionComponent.class);
            Entity tc = wc.getTileAt(pc.getX(), pc.getY());
            if (tc != null) {
                tc.getComponent(TileComponent.class).occupy(entity);
            }
        }
    }


    private static List<Entity> generateRandomClusters(double prob,
                                                       PositionComponent startPos,
                                                       List<PositionComponent> occupiedSpots,
                                                       EntityManager em){
        double n = Math.random();
        Resource resource = null;
        List<PositionComponent> locations = new ArrayList<>();
        List<Entity> nrelist= new ArrayList<>();
        if (n< 0.006){
            locations = generateCluster(prob, startPos, occupiedSpots);
            resource = new Resource ( 1000, ResourceType.WATER);
        } else if (n < 0.01) {
            locations = generateCluster(prob, startPos, occupiedSpots);
            resource = new Resource ( 1000, ResourceType.WOOD);
        } else if (n < 0.013) {
            locations = generateCluster(prob, startPos, occupiedSpots);
            resource = new Resource ( 1000, ResourceType.STONE);
        }
        for (PositionComponent loc : locations) {
            Entity naturalResourceEntity = NaturalResourceFactory.createNaturalResource(loc.getX(), loc.getY(), resource, em);
            nrelist.add(naturalResourceEntity);
            em.addEntityToEngine(naturalResourceEntity);
        }
        return nrelist;
    }

    private static List<PositionComponent> generateCluster(double prob,
                                                           PositionComponent startPos,
                                                           List<PositionComponent> occupiedSpots){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                PositionComponent pos = new PositionComponent(startPos.getX() + i, startPos.getY() + j);
                if (Math.random() < prob && !occupiedSpots.contains(pos)) {
                    occupiedSpots.add(pos);
                    generateCluster(prob*0.5,pos,occupiedSpots);
                }

            }
        }
        return occupiedSpots;
    }

    private static Entity createTileEntity(int x, int y, EntityManager engine) {
        Entity tile = engine.createEntity();
        tile.add(new TileComponent());
        tile.add(new PositionComponent(x, y));
        tile.add(new SizeComponent(1, 1));
        return tile;
    }
}
