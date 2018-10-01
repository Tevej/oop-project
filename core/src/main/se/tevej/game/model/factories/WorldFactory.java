package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;

public class WorldFactory {
    public static Entity createWorldEntity(int width, int height, EntityManager engine) {
        Entity[] tiles = new Entity[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity resource = generateRandomNaturalResource(x,y,engine);
                Entity tile = createTileEntity(x, y, resource, engine);
                tiles[x + y * width] = tile;
                if (resource != null) engine.addEntityToEngine(resource);
                engine.addEntityToEngine(tile);
            }
        }

        Entity worldEntity = engine.createEntity();
        worldEntity.add(new WorldComponent(width, height, tiles));
        return worldEntity;
    }

    private static Entity generateRandomNaturalResource(float x, float y, EntityManager engine) {
        double n = Math.random();
        if (n< 0.05){
            return NaturalResourceFactory.createNaturalWaterResource(x,y,engine);
        } else if (n < 0.07) {
            return NaturalResourceFactory.createNaturalStoneResource(x,y,engine);
        } else if (n < 0.1) {
            return NaturalResourceFactory.createNaturalWoodResource(x,y,engine);
        } else {
            return null;
        }
    }

    private static Entity createTileEntity(float x, float y, Entity occupier, EntityManager engine) {
        Entity tile = engine.createEntity();
        tile.add(new TileComponent(occupier));
        tile.add(new PositionComponent(x, y));
        tile.add(new SizeComponent(1, 1));
        return tile;
    }
}
