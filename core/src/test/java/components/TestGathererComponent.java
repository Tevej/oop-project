package components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.resources.MismatchedResourceException;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;
import org.junit.Test;

public class TestGathererComponent {

    public TestGathererComponent() {
        super();
    }

    @Test
    public void testMethod() {
        GathererComponent gc = new GathererComponent(new Resource(100, ResourceType.WOOD));
        assertEquals(gc.getGatheredResource(1f / 2f).getAmount(), 50, 0);
        try {
            gc.setResourcePerSecond(new Resource(200, ResourceType.WOOD));
            assertEquals(gc.getGatheredResource(3f / 2f).getAmount(), 300, 0);
        } catch (MismatchedResourceException e) {
            fail();
        }
    }
}
