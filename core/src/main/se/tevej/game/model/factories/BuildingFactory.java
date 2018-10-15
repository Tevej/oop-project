package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.RadiusComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

@SuppressWarnings("PMD") // The class is to be removed.
public class BuildingFactory {
    public static Entity createBuilding(
        BuildingType type, int x, int y) throws NoSuchBuildingException {
        Entity building = new Entity();
        building.add(new PositionComponent(x, y));
        building.add(new SizeComponent(1, 1));
        building.add(new BuildingComponent(type));
        switch (type) {
            case HOME:
                building = createHome(building);
                break;
            case LUMBERMILL:
                building = createLumberMill(building);
                break;
            case QUARRY:
                building = createQuarry(building);
                break;
            case PUMP:
                building = createPump(building);
                break;
            default:
                throw new NoSuchBuildingException(type.toString());
        }
        return building;
    }

    // Should only ever be called from createBuilding, hence private access.
    private static Entity createHome(Entity entity) {
        entity.add(new HomeComponent());
        return entity;
    }

    private static Entity createLumberMill(Entity entity) {
        entity.add(new RadiusComponent(3));
        entity.add(new GathererComponent(new Resource(50, ResourceType.WOOD)));
        return entity;
    }

    private static Entity createQuarry(Entity entity) {
        entity.add(new RadiusComponent(3));
        entity.add(new GathererComponent(new Resource(50, ResourceType.STONE)));
        return entity;
    }

    private static Entity createPump(Entity entity) {
        entity.add(new RadiusComponent(3));
        entity.add(new GathererComponent(new Resource(50, ResourceType.WATER)));
        return entity;
    }
}
