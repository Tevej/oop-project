package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.EntitySystem;
import main.se.tevej.game.exceptions.MissmatchedResourceException;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.components.GathererComponent;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.resource.Resource;

import java.util.HashMap;

public class NaturalResourceGatheringSystem extends EntitySystem{
    private InventoryComponent inventoryComponent;
    private HashMap<NaturalResourceComponent, GathererComponent> gatheringInterlaceMap;

    public NaturalResourceGatheringSystem(InventoryComponent inventoryComponent) {
        this.inventoryComponent = inventoryComponent;
        this.gatheringInterlaceMap = new HashMap<>();
    }

    public void addGathering(NaturalResourceComponent n, GathererComponent g) throws MissmatchedResourceException{
        if (n.getType() == g.getResourcePerSecond().getType()){
            gatheringInterlaceMap.put(n,g);
        } else {
            throw new MissmatchedResourceException();
        }
    }

    @Override
    public void update(float deltaTime){
        for (NaturalResourceComponent naturalResource : gatheringInterlaceMap.keySet()) {
            try {
                Resource gatheredResource = gatheringInterlaceMap.get(naturalResource).getGatheredResource(deltaTime);
                naturalResource.extractResource(gatheredResource);
                inventoryComponent.addResource(gatheredResource);

            } catch (NotEnoughResourcesException e) {
                System.out.println("Not enough resource left");
            }
        }
    }
}
