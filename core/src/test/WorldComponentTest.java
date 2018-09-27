package test;

import com.badlogic.ashley.core.Entity;
import org.junit.Test;
import se.tevej.game.ashley.EntityManager;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.WorldComponent;
import se.tevej.game.model.factories.WorldFactory;

import static org.junit.Assert.*;

public class WorldComponentTest {
    @Test
    public void getTileAt() {
        Entity world = WorldFactory.CreateWorldEntity(32, 75, new EntityManager());
        WorldComponent wc = world.getComponent(WorldComponent.class);

        assertTrue(
                wc.getTileAt(1,1).getComponent(PositionComponent.class).getX() == 0
        );
        assertTrue(
                wc.getTileAt(31, 74).getComponent(PositionComponent.class).getX() == 31
        );
        assertTrue(
                wc.getTileAt(0,10).getComponent(PositionComponent.class).getX() == 0
        );

    }
}
