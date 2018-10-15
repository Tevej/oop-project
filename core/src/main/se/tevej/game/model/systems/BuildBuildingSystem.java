package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.entities.BuildingEntity;

public class BuildBuildingSystem extends EntitySystem implements SignalListener {

    private Engine engine;

    public BuildBuildingSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setSignal(Signal<Entity> signal) {
    }

    @Override
    public Listener<Entity> getSignalListener() {
        return new Listener<Entity>() {
            @Override
            public void receive(Signal<Entity> signal, Entity signalEntity) {
                SignalComponent signalComponent = signalEntity.getComponent(SignalComponent.class);
                switch (signalComponent.getType()) {
                    case BUILDBUILDING:
                        BuildingComponent buildingC =
                            signalEntity.getComponent(BuildingComponent.class);
                        PositionComponent posC = signalEntity.getComponent(PositionComponent.class);
                        Entity tile = signalEntity.getComponent(WorldComponent.class)
                            .getTileAt((int) posC.getX(), (int) posC.getY());
                        System.out.println(posC.getX() + ", " + posC.getY());
                        TileComponent tileC = tile.getComponent(TileComponent.class);
                        buildBuilding(tileC, posC, buildingC.getType());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void buildBuilding(
        TileComponent tileC, PositionComponent posC, BuildingType buildingType) {

        Entity building;
        try {
            building = new BuildingEntity(buildingType, posC.getX(), posC.getY());
        } catch (NoSuchBuildingException e) {
            return;
        }
        engine.addEntity(building);
        tileC.occupy(building);
    }
}