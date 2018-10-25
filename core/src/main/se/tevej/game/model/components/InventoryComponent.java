package main.se.tevej.game.model.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Component;

import main.se.tevej.game.model.resources.NotEnoughResourcesException;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;

/**
 * There is only supposed to be on entity with an inventory component and it represents the
 * players inventory.
 */
public class InventoryComponent implements Component {
    private Map<ResourceType, Double> typeToAmount;

    public InventoryComponent() {
        typeToAmount = new HashMap<ResourceType, Double>();
    }

    public void addResource(Resource resource) {
        if (typeToAmount.containsKey(resource.getType())) {
            typeToAmount.put(resource.getType(),
                typeToAmount.get(resource.getType()) + resource.getAmount());
        } else {
            typeToAmount.put(resource.getType(), resource.getAmount());
        }
    }

    public double getAmountOfResource(ResourceType type) {
        double amount = 0;
        if (typeToAmount.containsKey(type)) {
            amount = typeToAmount.get(type);
        }
        return amount;
    }

    private void checkResourceAmount(Resource resource) throws NotEnoughResourcesException {
        double amount = getAmountOfResource(resource.getType());
        if (resource.getAmount() > amount) {
            throw new NotEnoughResourcesException();
        }
    }

    public void removeFromInventory(Resource resource) throws NotEnoughResourcesException {
        checkResourceAmount(resource);
        typeToAmount.put(resource.getType(),
            getAmountOfResource(resource.getType()) - resource.getAmount());
    }

    public void removeFromInventory(List<Resource> resourceList)
        throws NotEnoughResourcesException {

        for (Resource resource : resourceList) {
            checkResourceAmount(resource);
        }
        for (Resource resource : resourceList) {
            typeToAmount.put(resource.getType(),
                getAmountOfResource(resource.getType()) - resource.getAmount());
        }

    }
}