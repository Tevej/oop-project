package se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import se.tevej.game.model.resource.Resource;
import se.tevej.game.model.resource.ResourceType;

import java.util.HashMap;
import java.util.List;

public class CostComponent implements Component {
    private List<Resource> resources;

    public Resource getCostOfResource(ResourceType type) {
        for (Resource resource : resources) {
            return resource;
        }
    }
}