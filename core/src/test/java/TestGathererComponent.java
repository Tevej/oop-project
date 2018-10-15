import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.exceptions.MissmatchedResourceException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestGathererComponent {
    @Test
    public void testMethod() {
        GathererComponent gc = new GathererComponent(new Resource(100, ResourceType.WOOD));
        assertTrue(gc.getGatheredResource(1f/2f).getAmount() == 50);
        try {
            gc.setResourcePerSecond(new Resource(200, ResourceType.WOOD));
            assertTrue(gc.getGatheredResource(3f/2f).getAmount() == 300);
        } catch (MissmatchedResourceException e) {
            assertTrue(false);
        }
    }
}
