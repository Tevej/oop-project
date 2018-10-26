package main.java.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;

import main.java.se.tevej.game.model.resources.NotEnoughResourcesException;
import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.resources.ResourceType;

public class NaturalResourceComponent implements Component {
    private Resource resource;
    private boolean undepletable = false;

    public NaturalResourceComponent(Resource resource) {
        this.resource = resource;
        if (resource.getAmount() < 0) {
            undepletable = true;
        }
    }

    public double getAmountLeft() {
        return resource.getAmount();
    }

    public void extractResource(Resource extractedResource) throws NotEnoughResourcesException {
        if (undepletable) {
            return;
        } else if (resource.getAmount() < extractedResource.getAmount()) {
            throw new NotEnoughResourcesException();
        }
        resource = this.resource.updateAmount(resource.getAmount() - extractedResource.getAmount());
    }

    public ResourceType getType() {
        return resource.getType();
    }
}
