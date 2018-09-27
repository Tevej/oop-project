package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.model.resource.ResourceType;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;

public class NaturalResourceComponent implements Component {
    private ResourceType type;
    private float amount;

    public NaturalResourceComponent(ResourceType type, float amount) {
        this.type = type;
        this.amount = amount;
    }

    public float getAmountLeft() {
        return amount;
    }

    public void extractResource(float amount) throws NotEnoughResourcesException {
        if (this.amount < amount) {
            throw new NotEnoughResourcesException();
        }

        this.amount -= amount;
    }

    public ResourceType getType() {
        return type;
    }
}
