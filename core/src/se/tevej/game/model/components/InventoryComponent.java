package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import se.tevej.game.exceptions.NotEnoughResourcesException;
import se.tevej.game.model.resource.Resource;
import se.tevej.game.model.resource.ResourceType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

public class InventoryComponent implements Component {
    private HashMap<ResourceType, Float> resources;

    public InventoryComponent() {
        resources = new HashMap<ResourceType, Float>();
    }

    public void addResource(Resource resource) {
        if (resources.containsKey(resource.getType())) {
            resources.put(resource.getType(), resources.get(resource.getType()) + resource.getAmount());
        }
        else {
            resources.put(resource.getType(), resource.getAmount());
        }
    }

    public float getAmountOfResource(ResourceType type) {
        return (float)resources.get(type);
    }

    public void removeFromInventory(ResourceType type, float amount) throws Exception {
        float currAmountOfResource = getAmountOfResource(type);
        if (amount > currAmountOfResource) {
            throw new NotEnoughResourcesException();
        }

        resources.put(type, currAmountOfResource - amount);
    }
}
