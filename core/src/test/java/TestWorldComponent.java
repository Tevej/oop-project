import static org.junit.Assert.assertTrue;

import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.factories.WorldFactory;
import org.junit.Test;

public class TestWorldComponent {
    EntityManager em = new EntityManager();
    WorldComponent wc = WorldFactory.createWorldEntity(34, 23, em).getComponent(WorldComponent.class);

    @Test
    public void getTilesAt() {
        assertTrue(
            wc.getTileAt(1, 1).getComponent(PositionComponent.class).getX() == 1
        );
        assertTrue(
            wc.getTileAt(1, 1).getComponent(PositionComponent.class).getY() == 1
        );
        assertTrue(
            wc.getTileAt(20, 0).getComponent(PositionComponent.class).getX() == 20
        );
        assertTrue(
            wc.getTileAt(20, 0).getComponent(PositionComponent.class).getY() == 0
        );
        assertTrue(
            wc.getTileAt(33, 22).getComponent(PositionComponent.class).getX() == 33
        );
        assertTrue(
            wc.getTileAt(33, 22).getComponent(PositionComponent.class).getY() == 22
        );

    }
}