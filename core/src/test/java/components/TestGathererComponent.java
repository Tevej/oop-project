package components;

import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.exceptions.MismatchedResourceException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestGathererComponent {

    public TestGathererComponent() {
        super();
    }

    @Test
    public void testMethod() {
        GathererComponent gc = new GathererComponent(new Resource(100, ResourceType.WOOD));
        assertEquals(gc.getGatheredResource(1f/2f).getAmount(), 50, 0);
        try {
            gc.setResourcePerSecond(new Resource(200, ResourceType.WOOD));
            assertEquals(gc.getGatheredResource(3f/2f).getAmount(), 300, 0);
        } catch (MismatchedResourceException e) {
            fail();
        }
    }
}
