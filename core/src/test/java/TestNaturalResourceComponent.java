import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestNaturalResourceComponent {

    @Test
    public void testMethod() {
        NaturalResourceComponent nrc = new NaturalResourceComponent
                (new Resource(20, ResourceType.WOOD));
        assertTrue(nrc.getAmountLeft() == 20);
        assertTrue(nrc.getType() == ResourceType.WOOD);
        try {
            nrc.extractResource(new Resource(19.9, ResourceType.WOOD));
            assertTrue(nrc.getAmountLeft() == 0.1);
        } catch (NotEnoughResourcesException e) {
            assertTrue(false);
        }
    }
}
