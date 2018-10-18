package main.se.tevej.game.model.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Component;

import main.se.tevej.game.model.resources.NotEnoughResourcesException;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;

public class InventoryComponent implements Component {
    private Map<ResourceType, Double> resources;

    public InventoryComponent() {
        resources = new HashMap<ResourceType, Double>();
    }

    public void addResource(Resource resource) {
        if (resources.containsKey(resource.getType())) {
            resources.put(resource.getType(),
                resources.get(resource.getType()) + resource.getAmount());
        } else {
            resources.put(resource.getType(), resource.getAmount());
        }
    }

    public double getAmountOfResource(ResourceType type) {
        double amount = 0;
        if (resources.containsKey(type)) {
            amount = resources.get(type);
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
        resources.put(resource.getType(),
            getAmountOfResource(resource.getType()) - resource.getAmount());
    }

    public void removeFromInventory(List<Resource> resourceList)
        throws NotEnoughResourcesException {

        for (Resource resource : resourceList) {
            checkResourceAmount(resource);
        }
        for (Resource resource : resourceList) {
            resources.put(resource.getType(),
                getAmountOfResource(resource.getType()) - resource.getAmount());
        }

    }
}