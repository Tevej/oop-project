package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.exceptions.MissmatchedResourceException;
import main.se.tevej.game.model.resource.Resource;

public class GathererComponent implements Component{
    private Resource resourcePerSecond;
    private int radius;
    public GathererComponent() {

    }

    public Resource getResourcePerSecond(){
        return resourcePerSecond;
    }

    public int getRadius(){
        return radius;
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
