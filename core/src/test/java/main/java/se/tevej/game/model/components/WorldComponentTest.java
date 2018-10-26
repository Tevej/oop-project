package main.java.se.tevej.game.model.components;

import static org.junit.Assert.assertEquals;

import main.java.se.tevej.game.model.ModelManager;
import main.java.se.tevej.game.model.entities.WorldEntity;
import org.junit.Test;

class WorldComponentTest {
    private ModelManager modelManager = new ModelManager(100, 100);
    private WorldComponent worldEntity = new WorldEntity(34, 23, modelManager).getComponent(WorldComponent.class);

    public WorldComponentTest() {
        super();
    }

    @Test
    public void testGetTilesAt() {
        assertEquals(
            worldEntity.getTileAt(1, 1).getComponent(PositionComponent.class).getX(), 1
        );
        assertEquals(
            worldEntity.getTileAt(1, 1).getComponent(PositionComponent.class).getY(), 1
        );
        assertEquals(
            worldEntity.getTileAt(20, 0).getComponent(PositionComponent.class).getX(), 20
        );
        assertEquals(
            worldEntity.getTileAt(20, 0).getComponent(PositionComponent.class).getY(), 0
        );
        assertEquals(
            worldEntity.getTileAt(33, 22).getComponent(PositionComponent.class).getX(), 33
        );
        assertEquals(
            worldEntity.getTileAt(33, 22).getComponent(PositionComponent.class).getY(), 22
        );
    }

    @Test
    public void testDimensions() {
        assertEquals(worldEntity.getHeight(), 23);
        assertEquals(worldEntity.getWidth(), 34);
    }
}