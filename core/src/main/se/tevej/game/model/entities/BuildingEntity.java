package main.se.tevej.game.model.entities;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.FarmComponent;
import main.se.tevej.game.model.components.buildings.FarmLandComponent;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;


public class BuildingEntity extends Entity {

    private AddToEngineListener engineListener;

    public BuildingEntity(
        WorldComponent worldC, BuildingType type, int x, int y, AddToEngineListener engineListener)
        throws NoSuchBuildingException {
        super();
        this.add(new PositionComponent(x, y));
        this.add(new SizeComponent(1, 1));
        this.add(new BuildingComponent(type));
        switch (type) {
            case HOME:
                createHome();
                break;
            case LUMBERMILL:
                createLumberMill();
                break;
            case QUARRY:
                createQuarry();
                break;
            case PUMP:
                createPump();
                break;
            case FARM:
                createFarm(worldC, engineListener);
                break;
            default:
                throw new NoSuchBuildingException(type.toString());
        }
    }

    private void createFarm(WorldComponent worldC, AddToEngineListener engineListener) {
        Entity[] neighbours = worldC.getTileNeighbours(this, true);

        List<Entity> emptyNeighbours = new LinkedList<>();

        for (Entity neighbour : neighbours) {
            PositionComponent posC = neighbour.getComponent(PositionComponent.class);
            if (!worldC.isTileOccupied(posC.getX(), posC.getY())) {
                emptyNeighbours.add(neighbour);
            }
        }

        List<Entity> farmLandEntities = new LinkedList<>();

        for (Entity entity : emptyNeighbours) {
            PositionComponent posC = entity.getComponent(PositionComponent.class);
            Entity farmLand = createFarmLand(posC);
            farmLandEntities.add(farmLand);
            worldC.occupyTile(posC.getX(), posC.getY(), farmLand);
            engineListener.addEntityToEngine(farmLand);
        }
        this.add(new FarmComponent(farmLandEntities));
    }

    private Entity createFarmLand(PositionComponent posC) {
        Entity entity = new Entity();
        entity.add(new SizeComponent(1, 1));
        entity.add(new FarmLandComponent(new Resource(5, ResourceType.FOOD)));
        entity.add(new BuildingComponent(BuildingType.FARM_LAND));
        entity.add(posC.clone());
        return entity;
    }


    // Should only ever be called from createBuilding, hence private access.
    private void createHome() {
        this.add(new HomeComponent());
    }

    private void createLumberMill() {
        this.add(new RadiusComponent(10));
        this.add(new GathererComponent(new Resource(5, ResourceType.WOOD)));
    }

    private void createQuarry() {
        this.add(new RadiusComponent(10));
        this.add(new GathererComponent(new Resource(5, ResourceType.STONE)));
    }

    private void createPump() {
        this.add(new RadiusComponent(1));
        this.add(new GathererComponent(new Resource(2.5, ResourceType.WATER)));
    }

}
