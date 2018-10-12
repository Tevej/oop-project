package test.java;

import static org.junit.Assert.assertTrue;

import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.entities.WorldEntity;
import org.junit.Test;

public class TestWorldComponent {
    private EntityManager entityManager = new EntityManager();
    private WorldComponent worldEntity = new WorldEntity(34, 23, entityManager).getComponent(WorldComponent.class);

    public TestWorldComponent() {
        super();
    }

    @Test
    public void testGetTilesAt() {
        assertTrue(
            worldEntity.getTileAt(1, 1).getComponent(PositionComponent.class).getX() == 1
        );
        assertTrue(
            worldEntity.getTileAt(1, 1).getComponent(PositionComponent.class).getY() == 1
        );
        assertTrue(
            worldEntity.getTileAt(20, 0).getComponent(PositionComponent.class).getX() == 20
        );
        assertTrue(
            worldEntity.getTileAt(20, 0).getComponent(PositionComponent.class).getY() == 0
        );
        assertTrue(
            worldEntity.getTileAt(33, 22).getComponent(PositionComponent.class).getX() == 33
        );
        assertTrue(
            worldEntity.getTileAt(33, 22).getComponent(PositionComponent.class).getY() == 22
        );

    }
}