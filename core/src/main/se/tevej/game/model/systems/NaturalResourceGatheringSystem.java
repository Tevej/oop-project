package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.*;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class NaturalResourceGatheringSystem extends EntitySystem{

    private Engine engine;
    private EntityManager em;

    public NaturalResourceGatheringSystem(EntityManager em){
        this.em = em;
    }

    private List<double[]> getLocationsInRadius(int radius, PositionComponent positionComponent, int maxWidth, int maxHeight){
        List<double[]> locations = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++){
                if ( i == 0 && j == 0) {
                    continue;
                }
                double x = i+positionComponent.getX();
                double y = j+positionComponent.getY();
                if (x < 0 || y < 0 || x >= maxWidth || y >= maxHeight) {
                    continue;
                }
                locations.add(new double[] {x, y});
            }

        }
        return locations;
    }

    private void gatherFromLocation(float deltaTime, Entity occupier, InventoryComponent ic, GathererComponent gc){
        NaturalResourceComponent naturalResourceComponent = occupier.getComponent(NaturalResourceComponent.class);
        if (naturalResourceComponent == null ||
                naturalResourceComponent.getType() != gc.getResourcePerSecond().getType()) {
            return;
        }
        try {
            Resource gatheredResource = gc.getGatheredResource(deltaTime);
            naturalResourceComponent.extractResource(gatheredResource);
            ic.addResource(gatheredResource);
        } catch (NotEnoughResourcesException e) {
            // Extracts the resource left and deletes entity
            ic.addResource(naturalResourceComponent.getResource());
            occupier.add(new SignalComponent(SignalType.DELETEENTITY));
            em.getSignal().dispatch(occupier);
            System.out.println("Not enough utils left");
        }
    }

    private Entity getOccupierAtLocation(WorldComponent wc, double[] location){
        Entity tileEntity = wc.getTileAt((int)location[0], (int)location[1]);
        TileComponent tileComponent = tileEntity.getComponent(TileComponent.class);
        return tileComponent.getOccupier();
    }

    private void gather(Entity gatherer, int maxWidth, int maxHeight, WorldComponent wc, InventoryComponent ic, float deltaTime){
        // Saves variables
        GathererComponent gc = gatherer.getComponent(GathererComponent.class);
        int radius = gatherer.getComponent(RadiusComponent.class).getRadius();

        List<double[]> locations = getLocationsInRadius(radius,
                gatherer.getComponent(PositionComponent.class), maxWidth, maxHeight);

        for (double[] loc : locations) {
            Entity occupier = getOccupierAtLocation(wc, loc);
            if (occupier == null) {
                continue;
            }
            gatherFromLocation(deltaTime, occupier, ic, gc);
        }
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime){
        // Gets all Entities that will gather natural resources
        ImmutableArray<Entity> gatherers = engine.getEntitiesFor(Family.all(GathererComponent.class, PositionComponent.class).get());

        // Saves necessary variables for later use
        InventoryComponent ic = engine.getEntitiesFor(Family.all(InventoryComponent.class).get())
                .first().getComponent(InventoryComponent.class);
        WorldComponent wc = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
                .first().getComponent(WorldComponent.class);
        int maxWidth = wc.getWidth();
        int maxHeight = wc.getHeight();

        for (Entity gatherer : gatherers) {
            gather(gatherer, maxWidth, maxHeight, wc, ic, deltaTime);
        }
    }

}
