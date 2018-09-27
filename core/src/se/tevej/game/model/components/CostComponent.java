package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import se.tevej.game.model.resource.Resource;
import se.tevej.game.model.resource.ResourceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CostComponent implements Component {
    private List<Resource> resources;

    public CostComponent() {
        resources = new ArrayList<Resource>();
    }

    public double getCostOfResource(ResourceType type) {
        for (Resource resource : resources) {
            if (type == resource.getType()) {
                return resource.getAmount();
            }
        }
        return 0;
    }
}