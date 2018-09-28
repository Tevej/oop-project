package main.se.tevej.game.model.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.HomeComponent;

public class BuildingFactory {
    public static Entity createBuilding(BuildingType type) throws NoSuchBuildingException {
        switch(type)
        {
            case HOME:
                return createHome();
            default:
                throw new NoSuchBuildingException(type);
        }
    }

    public static Entity createHome() {
        Entity entity = new Entity();
        entity.add(new HomeComponent());
        return entity;
    }
}
