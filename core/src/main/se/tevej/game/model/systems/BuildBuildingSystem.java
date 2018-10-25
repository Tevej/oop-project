package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.entities.AddToEngineListener;
import main.se.tevej.game.model.entities.BuildingEntity;
import main.se.tevej.game.model.entities.NoSuchBuildingException;
import main.se.tevej.game.model.signals.SignalComponent;

/**
 * This system's only purpose is to build buildings and place them on the map and it is the only
 * class with that purpose.
 */
public class BuildBuildingSystem extends TSystem implements AddToEngineListener {

    private Engine engine;

    public BuildBuildingSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void receive(Signal<Entity> signal, Entity signalEntity) {
        SignalComponent signalComponent = signalEntity.getComponent(SignalComponent.class);
        switch (signalComponent.getType()) {
            case BUILD_BUILDING:
                BuildingComponent buildingC =
                    signalEntity.getComponent(BuildingComponent.class);
                PositionComponent posC = signalEntity.getComponent(PositionComponent.class);
                Entity tile = signalEntity.getComponent(WorldComponent.class)
                    .getTileAt((int) posC.getX(), (int) posC.getY());
                TileComponent tileC = tile.getComponent(TileComponent.class);
                buildBuilding(tileC, posC, buildingC.getType());
                break;
            default:
                break;
        }
    }

    private void buildBuilding(
        TileComponent tileC, PositionComponent posC, BuildingType buildingType) {

        Entity building;
        try {
            building = new BuildingEntity(
                engine.getEntitiesFor(Family.all(WorldComponent.class).get()).first()
                    .getComponent(WorldComponent.class),
                buildingType,
                posC.getX(),
                posC.getY(),
                this
            );
        } catch (NoSuchBuildingException e) {
            return;
        }

        engine.addEntity(building);
        tileC.occupy(building);
    }

    @Override
    public void addEntityToEngine(Entity entity) {
        engine.addEntity(entity);
    }
}