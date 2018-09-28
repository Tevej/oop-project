package se.tevej.game.model.components;

import se.tevej.game.exceptions.MissmatchedResourceException;
import se.tevej.game.model.resource.Resource;

public class GathererComponent {
    private Resource resourcePerSecond;
    public GathererComponent() {

    }

    public Resource getResourcePerSecond(){
        return resourcePerSecond;
    }

    public Resource getGatheredResource(float deltaTime){
        return new Resource(resourcePerSecond.getAmount()*deltaTime,
                resourcePerSecond.getType());
    }

    public void setResourcePerSecond(Resource newSpeed) throws MissmatchedResourceException{
        if (newSpeed.getType() == resourcePerSecond.getType()) {
            this.resourcePerSecond = resourcePerSecond.setAmount(newSpeed.getAmount());
        } else {
            throw new MissmatchedResourceException();
        }
    }
}
