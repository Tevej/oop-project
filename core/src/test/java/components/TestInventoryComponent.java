package components;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class TestInventoryComponent {

    public TestInventoryComponent() {
        super();
    }

    @Test
    public void addResource(){
        InventoryComponent ic = new InventoryComponent();

        Resource r1 = new Resource(10, ResourceType.STONE);
        Resource r2 = new Resource(0.1, ResourceType.STONE);
        Resource r3 = new Resource(0.9, ResourceType.STONE);
        ic.addResource(r1);
        assertEquals(ic.getAmountOfResource(ResourceType.STONE),  10, 0);
        ic.addResource(r2);
        assertTrue(ic.getAmountOfResource(ResourceType.STONE) > 10);
        ic.addResource(r3);
        assertEquals(ic.getAmountOfResource(ResourceType.STONE), 11, 0);
    }

    @Test
    public void removeFromInventory(){
        InventoryComponent ic = new InventoryComponent();

        Resource r1 = new Resource(10, ResourceType.STONE);
        Resource r2 = new Resource(5, ResourceType.STONE);
        Resource r3 = new Resource(-100, ResourceType.WOOD);
        List<Resource> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);
        list.add(r3);

        try {
            ic.addResource(r1);
            ic.removeFromInventory(r1);
            assertEquals(ic.getAmountOfResource(r1.getType()), 0, 0);
        } catch (NotEnoughResourcesException e) {
            // should not throw error
            fail();
        }

        try {
            ic.removeFromInventory(r2);
            // should throw error
            fail();
        } catch (NotEnoughResourcesException e) {
            System.out.println("yep");
        }


        try {
            ic.addResource(new Resource(0,ResourceType.WOOD));
            ic.removeFromInventory(r3);
            assertEquals(ic.getAmountOfResource(r3.getType()), 100, 0);
        } catch (NotEnoughResourcesException e) {
            // should not throw error
            fail();
        }

        InventoryComponent ic2 = new InventoryComponent();
        for (Resource resource : list) {
            ic2.addResource(resource);
        }
        try {
            ic2.removeFromInventory(list);
            assertEquals(ic2.getAmountOfResource(ResourceType.STONE),  0, 0);
            assertEquals(ic2.getAmountOfResource(ResourceType.WOOD), 0, 0);
        } catch (NotEnoughResourcesException e) {
            fail();
        }
    }
}
