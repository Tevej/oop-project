package se.tevej.game.model.components;

import se.tevej.game.model.resource.Resource;

public class GathererComponent {
    private Resource resourcePerSecond;
    public GathererComponent() {

    }

    public Resource getResourcePerSecond(){
        return resourcePerSecond;
    }

    public void setResourcePerSecond(Resource newSpeed){
        this.resourcePerSecond.setAmount(newSpeed.getAmount());
    }
}
