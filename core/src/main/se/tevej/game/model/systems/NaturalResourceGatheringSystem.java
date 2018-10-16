package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.sun.javafx.geom.Vec2f;

import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

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
            System.out.println("Not enough resource left");
        }
    }

    private Entity getOccupierAtLocation(WorldComponent world, Vec2f location) {
        Entity tileEntity = world.getTileAt((int)location.x, (int)location.y);
        TileComponent tileComponent = tileEntity.getComponent(TileComponent.class);
        return tileComponent.getOccupier();
    }

    private float getDistance(Vec2f location, PositionComponent gathererPosition) {
        return location.distance(gathererPosition.getX(), gathererPosition.getY());
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
    private boolean isInvalidLocation(Vec2f location, Entity gatherer, WorldComponent world) {
        boolean result = true;
        ResourceType gatherType = gatherer.getComponent(GathererComponent.class)
                .getResourcePerSecond().getType();
        if (!isOutOfBounds((int) location.x, (int) location.y,
                world.getWidth(), world.getHeight())) {
            Entity occupier = getOccupierAtLocation(world, location);
            result = occupier == null
                || occupier.getComponent(NaturalResourceComponent.class) == null
                || occupier.getComponent(NaturalResourceComponent.class).getType() != gatherType;
        }
        return result;
    }

    // Returns the location of the nearest location to the gatherer
    private Vec2f nearestLocation(PositionComponent gathererPosition,
                                  List<Vec2f> locations) {
        Vec2f nearest = locations.get(0);
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
    private List<Vec2f> getLocationsInRadius(int radius, Entity gatherer, WorldComponent world) {

        PositionComponent gatherPosition = gatherer.getComponent(PositionComponent.class);

        List<Vec2f> locations = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                Vec2f loc = new Vec2f(i + gatherPosition.getX(), j + gatherPosition.getY());
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
        List<Vec2f> locations = getLocationsInRadius(radius, gatherer, world);
        if (locations.isEmpty()) {
            return;
        }

        PositionComponent positionC = gatherer.getComponent(PositionComponent.class);
        GathererComponent gatherC = gatherer.getComponent(GathererComponent.class);

        Vec2f nearest = nearestLocation(positionC, locations);
        float distance = nearest.distance(positionC.getX(), positionC.getY());

        Entity occupier = getOccupierAtLocation(world, nearest);
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
