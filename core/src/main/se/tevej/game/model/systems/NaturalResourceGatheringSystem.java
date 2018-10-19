package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;

import main.se.tevej.game.model.signals.SignalComponent;
import main.se.tevej.game.model.signals.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.resources.NotEnoughResourcesException;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;

public class NaturalResourceGatheringSystem extends EntitySystem {

    private Engine engine;
    private SignalHolder signalHolder;

    public NaturalResourceGatheringSystem(SignalHolder signalHolder) {
        super();
        this.signalHolder = signalHolder;
    }

    // Gathers resources from the occupier with the speed depending on:
    // deltaTime, distance to resource multiplied by 2 and the gatherer's own field
    private void gatherFromLocation(float deltaTime, float distance,
                                    Entity occupier,
                                    InventoryComponent inventory,
                                    GathererComponent gathererC) {
        NaturalResourceComponent naturalResourceC = occupier
                .getComponent(NaturalResourceComponent.class);
        try {
            // Calculates the Resource gathered
            Resource gatheredResource = gathererC
                    .getGatheredResource(deltaTime / 2 / distance);

            // Moves the Resource from the natural resource to inventory
            naturalResourceC.extractResource(gatheredResource);
            inventory.addResource(gatheredResource);

        } catch (NotEnoughResourcesException e) {
            // Extracts the resource left and deletes entity
            Resource remainingResource = new Resource(naturalResourceC.getAmountLeft(),
                    naturalResourceC.getType());
            inventory.addResource(remainingResource);
            occupier.add(new SignalComponent(SignalType.DELETEENTITY));
            signalHolder.getSignal().dispatch(occupier);
        }
    }

    private float getDistance(Vector2 location, PositionComponent gathererPosition) {
        return location.dst(gathererPosition.getX(), gathererPosition.getY());
    }

    private boolean isOutOfBounds(int x, int y, int maxWidth, int maxHeight) {
        return x == 0 && y == 0
                || x < 0
                || y < 0
                || x >= maxWidth
                || y >= maxHeight;
    }

    // Returns true if all of the following are true:
    // the location is out of world bounds,
    // the location has no occupier,
    // the occupier of the location is null,
    // the occupier of the location has another ResourceType than the gatherer
    private boolean isInvalidLocation(Vector2 location, Entity gatherer, WorldComponent world) {
        boolean result = true;
        ResourceType gatherType = gatherer.getComponent(GathererComponent.class)
                .getResourcePerSecond().getType();
        if (!isOutOfBounds((int) location.x, (int) location.y,
                world.getWidth(), world.getHeight())) {
            Entity occupier = world.getTileOccupier((int)location.x, (int)location.y);

            result = occupier == null
                || occupier.getComponent(NaturalResourceComponent.class) == null
                || occupier.getComponent(NaturalResourceComponent.class).getType() != gatherType;
        }
        return result;
    }

    // Returns the location of the nearest location to the gatherer
    private Vector2 nearestLocation(PositionComponent gathererPosition,
                                  List<Vector2> locations) {
        Vector2 nearest = locations.get(0);
        double nearestDistance = getDistance(nearest, gathererPosition);
        for (int i = 1; i < locations.size(); i++) {
            double distance = getDistance(locations.get(i),gathererPosition);
            if (distance < nearestDistance) {
                nearest = locations.get(i);
                nearestDistance = distance;
            }
        }
        return nearest;
    }

    // Returns a list of all the valid locations
    private List<Vector2> getLocationsInRadius(int radius, Entity gatherer, WorldComponent world) {

        PositionComponent gatherPosition = gatherer.getComponent(PositionComponent.class);

        List<Vector2> locations = new ArrayList<Vector2>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                Vector2 loc = new Vector2(i + gatherPosition.getX(), j + gatherPosition.getY());
                if (isInvalidLocation(loc, gatherer, world)) {
                    continue;
                }
                locations.add(loc);
            }

        }
        return locations;
    }

    private void gather(Entity gatherer,
                        WorldComponent world,
                        InventoryComponent inventory,
                        float deltaTime) {

        int radius = gatherer.getComponent(RadiusComponent.class).getRadius();
        List<Vector2> locations = getLocationsInRadius(radius, gatherer, world);
        if (locations.isEmpty()) {
            return;
        }

        PositionComponent positionC = gatherer.getComponent(PositionComponent.class);
        GathererComponent gatherC = gatherer.getComponent(GathererComponent.class);

        Vector2 nearest = nearestLocation(positionC, locations);
        float distance = nearest.dst(positionC.getX(), positionC.getY());

        Entity occupier = world.getTileOccupier((int) nearest.x,(int) nearest.y);
        gatherFromLocation(deltaTime, distance, occupier, inventory, gatherC);
    }


    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        // Gets all Entities that will gather natural resources
        ImmutableArray<Entity> gatherers = engine.getEntitiesFor(
                Family.all(GathererComponent.class, PositionComponent.class).get());

        // Saves necessary variables for later use
        InventoryComponent inventory = engine.getEntitiesFor(Family.all(InventoryComponent.class)
                .get()).first().getComponent(InventoryComponent.class);
        WorldComponent world = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
                .first().getComponent(WorldComponent.class);

        for (Entity gatherer : gatherers) {
            gather(gatherer, world, inventory, deltaTime);
        }
    }

}
