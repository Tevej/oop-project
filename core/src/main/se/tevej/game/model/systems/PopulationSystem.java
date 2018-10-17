package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class PopulationSystem extends EntitySystem {

    private Engine engine;
    private final float gestationPeriod = 10f;
    private float gestationProgress;

    public PopulationSystem() {
        super();
    }

    private void giveBirth() {
        ImmutableArray<Entity> homes = engine.getEntitiesFor(
                Family.all(HomeComponent.class).get());
        InventoryComponent inventoryC = engine.getEntitiesFor(
                Family.all(InventoryComponent.class).get())
                .first().getComponent(InventoryComponent.class);

        for (int i = 0; i < homes.size(); i++) {
            HomeComponent homeC = homes.get(i).getComponent(HomeComponent.class);
            if (homeC.getCurrentPopulation() < homeC.getMaxPopulation()) {
                homeC.updateCurrentPopulation((int) homeC.getCurrentPopulation() + 1);
                inventoryC.addResource(new Resource(1, ResourceType.CURRENTPOPULATION));
            }
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        gestationProgress += deltaTime;
        if (gestationProgress >= gestationPeriod) {
            giveBirth();
            gestationProgress = 0;
        }
        // insert population eating method
        //      check if all currentpopulation could eat, otherwise kill them
    }
}
