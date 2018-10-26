package main.java.se.tevej.game.model.components.buildings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import main.java.se.tevej.game.model.resources.MismatchedResourceException;
import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.resources.ResourceType;
import org.junit.Test;

class GathererComponentTest {

    public GathererComponentTest() { super(); }

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