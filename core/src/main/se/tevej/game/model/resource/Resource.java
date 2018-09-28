package main.se.tevej.game.model.resource;

public class Resource {
    private double amount;
    private ResourceType type;

    public Resource(double amount, ResourceType type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public ResourceType getType() {
        return type;
    }
}
