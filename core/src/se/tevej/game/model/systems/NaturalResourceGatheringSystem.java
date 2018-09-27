package se.tevej.game.model.systems;

import com.badlogic.ashley.core.EntitySystem;
import se.tevej.game.exceptions.MissmatchedResourceException;
import se.tevej.game.exceptions.NotEnoughResourcesException;
import se.tevej.game.model.components.InventoryComponent;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.GathererComponent;

import java.util.HashMap;

public class NaturalResourceGatheringSystem extends EntitySystem{
    private InventoryComponent inventoryComponent;
    private HashMap<NaturalResourceComponent, GathererComponent> gatheringInterlacementMap;

    public NaturalResourceGatheringSystem(InventoryComponent inventoryComponent) {
        this.inventoryComponent = inventoryComponent;
    }

    public void addGathering(NaturalResourceComponent n, GathererComponent g) throws MissmatchedResourceException{
        if (n.getType() == g.getResourcePerSecond().getType()){
            gatheringInterlacementMap.put(n,g);
        } else {
            throw new MissmatchedResourceException();
        }
    }

    @Override
    public void update(float deltaTime){
        for (NaturalResourceComponent i : gatheringInterlacementMap.keySet()) {
            try {
                i.extractResource(gatheringInterlacementMap.get(i).getGatheredResource(deltaTime));
                inventoryComponent.addResource(gatheringInterlacementMap.get(i).getGatheredResource(deltaTime));

            } catch (NotEnoughResourcesException e) {
                System.out.println("Not enough resource left");
            }
        }
    }
}
