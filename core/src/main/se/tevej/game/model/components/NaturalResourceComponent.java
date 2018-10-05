package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.resource.Resource;
import main.se.tevej.game.model.resource.ResourceType;

public class NaturalResourceComponent implements Component {
    private Resource resource;

    public NaturalResourceComponent(Resource resource) {
        this.resource = resource;
    }

    public double getAmountLeft() {
        return resource.getAmount();
    }

    public void extractResource(Resource extractedResource) throws NotEnoughResourcesException {
        if (resource.getAmount() < extractedResource.getAmount()) {
            throw new NotEnoughResourcesException();
        }
        resource = this.resource.setAmount(resource.getAmount()-extractedResource.getAmount());
    }

    public ResourceType getType() {
        return resource.getType();
    }
}