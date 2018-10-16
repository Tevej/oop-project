package components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

public class TestNaturalResourceComponent {

    public TestNaturalResourceComponent() {
        super();
    }

    @Test
    public void testMethod() {
        NaturalResourceComponent nrc = new NaturalResourceComponent
            (new Resource(20, ResourceType.WOOD));
        assertEquals(nrc.getAmountLeft(), 20, 0);
        assertEquals(nrc.getType(), ResourceType.WOOD);
        try {
            nrc.extractResource(new Resource(19.9, ResourceType.WOOD));
            assertTrue(nrc.getAmountLeft() < 0.1000001 && nrc.getAmountLeft() > 0.0999999);
        } catch (NotEnoughResourcesException e) {
            fail();
        }
    }
}
