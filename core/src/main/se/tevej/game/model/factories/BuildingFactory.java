package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.HomeComponent;

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
            default:
                throw new NoSuchBuildingException(type);
        }
    }

    public static Entity createHome(Entity entity) {
        entity.add(new HomeComponent());
        return entity;
    }
}
