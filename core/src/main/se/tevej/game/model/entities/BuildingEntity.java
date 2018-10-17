package main.se.tevej.game.model.entities;

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

public class BuildingEntity extends Entity {

    public BuildingEntity(BuildingType type, int x, int y) throws NoSuchBuildingException {
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
            default:
                throw new NoSuchBuildingException(type.toString());
        }
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
