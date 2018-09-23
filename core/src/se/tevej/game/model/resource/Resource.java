package se.tevej.game.model.resource;

public class Resource {
    private float amount;
    private ResourceType type;

    public Resource(float amount, ResourceType type) {
        this.amount = amount;
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public ResourceType getType() {
        return type;
    }
}
