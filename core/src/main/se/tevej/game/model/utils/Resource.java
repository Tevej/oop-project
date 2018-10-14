package main.se.tevej.game.model.utils;

public class Resource {
    private final double amount;
    private final ResourceType type;

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

    public Resource updateAmount(double amount) {
        return new Resource(amount, type);
    }

    public ResourceType getType() {
        return type;
    }
}
