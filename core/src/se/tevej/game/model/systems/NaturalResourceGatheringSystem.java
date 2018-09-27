package se.tevej.game.model.systems;

import se.tevej.game.exceptions.MissmatchedResourceException;
import se.tevej.game.exceptions.NotEnoughResourcesException;
import se.tevej.game.model.components.InventoryComponent;
import se.tevej.game.model.components.NaturalResourceComponent;
import se.tevej.game.model.components.GathererComponent;

import java.util.HashMap;

public class NaturalResourceGatheringSystem {
    private InventoryComponent inventoryComponent;
    private HashMap<NaturalResourceComponent, GathererComponent> gatheringInterlacementMap;

    public void addGathering(NaturalResourceComponent n, GathererComponent g) throws MissmatchedResourceException{
        if (n.getType() == g.getResourcePerSecond().getType()){
            gatheringInterlacementMap.put(n,g);
        } else {
            throw new MissmatchedResourceException();
        }
    }

    void update(){
        for (NaturalResourceComponent i : gatheringInterlacementMap.keySet()) {
            try {
                i.extractResource(gatheringInterlacementMap.get(i).getResourcePerSecond());
                inventoryComponent.addResource(gatheringInterlacementMap.get(i).getResourcePerSecond());

            } catch (NotEnoughResourcesException e) {
                System.out.println("Not enough resource left");
            }
        }
    }
}
