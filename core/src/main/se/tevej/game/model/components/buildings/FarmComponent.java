package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

import java.util.LinkedList;
import java.util.List;

public class FarmComponent implements Component {
    private Resource food;
    private List<Entity> farmLandEntities;

    public FarmComponent(List<Entity> farmLandEntities) {
        food = new Resource(0, ResourceType.FOOD);
        this.farmLandEntities = farmLandEntities;
    }

    public List<Entity> getFarmLandEntities() {
        return farmLandEntities;
    }

    public Resource getGatheredResources(float deltaTime) {
        double totalAmount = 0;
        for (Entity farmLand : farmLandEntities) {
            totalAmount += farmLand.getComponent(FarmLandComponent.class).getGatheredResource(deltaTime).getAmount();
        }
        return new Resource(totalAmount, ResourceType.FOOD);
    }

    public Resource getFood() {
        return food;
    }
}
