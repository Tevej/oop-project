package se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;

import main.se.tevej.game.model.resources.MismatchedResourceException;
import main.se.tevej.game.model.resources.Resource;

public class GathererComponent implements Component {
    private Resource resourcePerSecond;

    public GathererComponent(Resource resourcePerSecond) {
        this.resourcePerSecond = resourcePerSecond;
    }

    public Resource getResourcePerSecond() {
        return resourcePerSecond;
    }

    public void setResourcePerSecond(Resource newSpeed) throws MismatchedResourceException {
        if (newSpeed.getType() == resourcePerSecond.getType()) {
            this.resourcePerSecond = resourcePerSecond.updateAmount(newSpeed.getAmount());
        } else {
            throw new MismatchedResourceException();
        }
    }

    public Resource getGatheredResource(float deltaTime) {
        return new Resource(resourcePerSecond.getAmount() * deltaTime,
            resourcePerSecond.getType());
    }
}
