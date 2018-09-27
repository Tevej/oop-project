package se.tevej.game.model.components;

import se.tevej.game.model.resource.Resource;

public class GathererComponent {
    private Resource resourcePerSecond;
    public GathererComponent() {

    }

    public Resource getResourcePerSecond(){
        return resourcePerSecond;
    }

    public Resource getGatheredResource(float deltaTime){
        Resource r = new Resource(resourcePerSecond);
        r.setAmount(r.getAmount()*deltaTime);
        return r;
    }

    public void setResourcePerSecond(Resource newSpeed){
        this.resourcePerSecond.setAmount(newSpeed.getAmount());
    }
}
