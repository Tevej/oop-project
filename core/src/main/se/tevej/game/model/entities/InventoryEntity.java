package main.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class InventoryEntity extends Entity {
    public InventoryEntity() {
        super();
        InventoryComponent inventoryC = new InventoryComponent();
        this.add(inventoryC);
        inventoryC.addResource(new Resource(1000, ResourceType.WOOD));
        inventoryC.addResource(new Resource(1000, ResourceType.WATER));
        inventoryC.addResource(new Resource(1000, ResourceType.STONE));
        inventoryC.addResource(new Resource(10000, ResourceType.FOOD));

    }
}
