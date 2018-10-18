package main.se.tevej.game.model.components.buildings;

import java.util.List;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;


public class FarmComponent implements Component {
    private List<Entity> farmLandEntities;

    public FarmComponent(List<Entity> farmLandEntities) {
        this.farmLandEntities = farmLandEntities;
    }

    public List<Entity> getFarmLandEntities() {
        return farmLandEntities;
    }

    public Resource getGatheredResources(float deltaTime) {
        double totalAmount = 0;
        for (Entity farmLand : farmLandEntities) {
            totalAmount += farmLand.getComponent(FarmLandComponent.class)
                .getGatheredResource(deltaTime).getAmount();
        }
        return new Resource(totalAmount, ResourceType.FOOD);
    }
}
