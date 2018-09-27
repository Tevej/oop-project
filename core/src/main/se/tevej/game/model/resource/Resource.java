package main.se.tevej.game.model.resource;

public class Resource {
    private double amount;
    private ResourceType type;

    public Resource(double amount, ResourceType type) {
        this.amount = amount;
        this.type = type;
    }

    public Resource(Resource resource) {
        this.amount = resource.getAmount();
        this.type = resource.getType();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount){this.amount = amount;}

    public ResourceType getType() {
        return type;
    }
}
