package se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.TileComponent;
import se.tevej.game.model.components.WorldComponent;

public class WorldFactory {
    public static Entity createWorldEntity(int width, int height, EntityManager engine) {
        Entity[] tiles = new Entity[width * height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity tile = createTileEntity(x, y, engine);
                tiles[x + y * width] = tile;
                engine.addEntityToEngine(tile);
            }
        }

        Entity worldEntity = engine.createEntity();
        worldEntity.add(new WorldComponent(width, height, tiles));
        return worldEntity;
    }

    private static Entity createTileEntity(float x, float y, EntityManager engine) {
        Entity tile = engine.createEntity();
        //tile.add(new TileComponent());
        //tile.add(new PositionComponent(x, y));
        return tile;
    }
}
