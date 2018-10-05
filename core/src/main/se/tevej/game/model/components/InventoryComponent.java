package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;

import java.util.HashMap;
import java.util.List;

public class InventoryComponent implements Component {
    private HashMap<ResourceType, Double> resources;

    public InventoryComponent() {
        resources = new HashMap<ResourceType, Double>();
    }

    public void addResource(Resource resource) {
        if (resources.containsKey(resource.getType())) {
            resources.put(resource.getType(), resources.get(resource.getType()) + resource.getAmount());
        }
        else {
            resources.put(resource.getType(), resource.getAmount());
        }
    }

    public double getAmountOfResource(ResourceType type) {
        return (double)resources.get(type);
    }

    public void removeFromInventory(Resource resource) throws NotEnoughResourcesException {
        checkResourceAmount(resource);

        resources.put(resource.getType(), getAmountOfResource(resource.getType()) - resource.getAmount());
    }

    private void checkResourceAmount(Resource resource) throws NotEnoughResourcesException{
        double currAmountOfResource = getAmountOfResource(resource.getType());
        if (resource.getAmount() > currAmountOfResource) {
            throw new NotEnoughResourcesException();
        }
    }

    public void removeFromInventory(List<Resource> resourceList) throws NotEnoughResourcesException {
        for (Resource resource : resourceList) {
            checkResourceAmount(resource);
        }
        for (Resource resource : resourceList){
            resources.put(resource.getType(), getAmountOfResource(resource.getType()) - resource.getAmount());
        }

    }
}