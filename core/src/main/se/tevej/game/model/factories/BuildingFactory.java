package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class BuildingFactory {
    public static Entity createBuilding(BuildingType type, float x, float y) throws NoSuchBuildingException {
        Entity building = new Entity();
        building.add(new PositionComponent(x,y));
        building.add(new SizeComponent(1,1));
        building.add(new BuildingComponent(type));
        switch(type)
        {
            case HOME:
                return createHome(building);
            case LUMBERMILL:
                return createLumberMill(building);
            case QUARRY:
                return createQuarry(building);
            case PUMP:
                return createPump(building);
            default:
                throw new NoSuchBuildingException(type);
        }
    }

    // Should only ever be called from createBuilding, hence private access.
    private static Entity createHome(Entity entity) {
        entity.add(new HomeComponent());
        return entity;
    }

    private static Entity createLumberMill(Entity entity) {
        entity.add(new GathererComponent(3, new Resource(50, ResourceType.WOOD)));
        return entity;
    }

    private static Entity createQuarry(Entity entity) {
        entity.add(new GathererComponent(3, new Resource(50, ResourceType.STONE)));
        return entity;
    }

    private static Entity createPump(Entity entity) {
        entity.add(new GathererComponent(3, new Resource(50, ResourceType.WATER)));
        return entity;
    }
}
