package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.buildings.FarmComponent;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;


public class FoodGatheringSystem extends EntitySystem {

    private Engine engine;

    public FoodGatheringSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> farms = engine.getEntitiesFor(
                Family.all(FarmComponent.class, PositionComponent.class).get());

        InventoryComponent inventoryC = engine.getEntitiesFor(
                Family.all(InventoryComponent.class).get()
        ).first().getComponent(InventoryComponent.class);

        double totalAmount = 0;
        for (Entity farm : farms) {
            totalAmount +=
                farm.getComponent(FarmComponent.class).getGatheredResources(deltaTime).getAmount();
        }
        inventoryC.addResource(new Resource(totalAmount, ResourceType.FOOD));

    }
}
