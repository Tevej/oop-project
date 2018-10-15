package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import com.sun.javafx.geom.Vec2f;

import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class NaturalResourceGatheringSystem extends EntitySystem {

    private Engine engine;
    private EntityManager em;

    public NaturalResourceGatheringSystem(EntityManager em) {
        super();
        this.em = em;
    }

    private List<Vec2f> getLocationsInRadius(int radius, Entity gatherer, WorldComponent world) {

        PositionComponent gathererPosition = gatherer.getComponent(PositionComponent.class);

        List<Vec2f> locations = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                Vec2f loc = new Vec2f(i, j);
                if (validateLocation(loc, gatherer, world)) {
                    continue;
                }
                loc.set(loc.x + gathererPosition.getX(), loc.y + gathererPosition.getY());
                locations.add(loc);
            }

        }
        return locations;
    }

    private boolean isOutOfBounds(int vx, int vy,
                                  PositionComponent positionC,
                                  int maxWidth, int maxHeight) {
        int x = vx + positionC.getX();
        int y = vy + positionC.getY();
        return vx == 0 && vy == 0
                || x < 0
                || y < 0
                || x >= maxWidth
                || y >= maxHeight;
    }

    private void gatherFromLocation(float deltaTime, Entity occupier,
                                    InventoryComponent inventory, GathererComponent gathererC,
                                    float distance) {
        NaturalResourceComponent naturalResourceC = occupier
                .getComponent(NaturalResourceComponent.class);
        try {
            Resource gatheredResource = gathererC.getGatheredResource(deltaTime * distance);
            naturalResourceC.extractResource(gatheredResource);
            inventory.addResource(gatheredResource);
        } catch (NotEnoughResourcesException e) {
            // Extracts the resource left and deletes entity
            Resource remainingResource = naturalResourceC.getResource();
            inventory.addResource(remainingResource);
            occupier.add(new SignalComponent(SignalType.DELETEENTITY));
            em.getSignal().dispatch(occupier);
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

    // Returns true if all of the following is true:
    // the location has an occupier,
    // the location is in the world bounds,
    // the occupier of the location is not null and
    // the occupier of the location has the same ResourceType as the gatherer
    private boolean validateLocation(Vec2f location, Entity gatherer, WorldComponent world) {

        PositionComponent gatherPosition = gatherer.getComponent(PositionComponent.class);
        ResourceType gatherType = gatherer.getComponent(GathererComponent.class)
                .getResourcePerSecond().getType();
        Entity occupier = getOccupierAtLocation(world, location);

        return !(occupier == null
                || isOutOfBounds((int)location.x, (int)location.y, gatherPosition,
                world.getWidth(), world.getHeight())
                || occupier.getComponent(NaturalResourceComponent.class) != null
                || occupier.getComponent(NaturalResourceComponent.class).getType() == gatherType);
    }

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


    private void gather(Entity gatherer,
                        WorldComponent world,
                        InventoryComponent inventory,
                        float deltaTime) {
        // Saves variables
        PositionComponent positionC = gatherer.getComponent(PositionComponent.class);
        GathererComponent gatherC = gatherer.getComponent(GathererComponent.class);
        int radius = gatherer.getComponent(RadiusComponent.class).getRadius();

        List<Vec2f> locations = getLocationsInRadius(radius, gatherer, world);
        Vec2f nearest = nearestLocation(positionC, locations);
        float distance = nearest.distance(positionC.getX(), positionC.getY());

        Entity occupier = getOccupierAtLocation(world, nearest);
        gatherFromLocation(deltaTime, occupier, inventory, gatherC, distance);
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
