package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.exceptions.MismatchedResourceException;
import main.se.tevej.game.exceptions.MismatchedResourceException;
import main.se.tevej.game.model.utils.Resource;

public class GathererComponent implements Component{
    private Resource resourcePerSecond;

    public GathererComponent(Resource resourcePerSecond) {
        this.resourcePerSecond = resourcePerSecond;
    }

    public Resource getResourcePerSecond(){
        return resourcePerSecond;
    }

    public Resource getGatheredResource(float deltaTime){
        return new Resource(resourcePerSecond.getAmount()*deltaTime,
                resourcePerSecond.getType());
    }

    public void setResourcePerSecond(Resource newSpeed) throws MismatchedResourceException{
        if (newSpeed.getType() == resourcePerSecond.getType()) {
            this.resourcePerSecond = resourcePerSecond.setAmount(newSpeed.getAmount());
        } else {
            throw new MismatchedResourceException();
        }
    }
}
