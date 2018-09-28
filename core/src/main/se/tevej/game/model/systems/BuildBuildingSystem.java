package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import main.se.tevej.game.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.factories.BuildingFactory;

public class BuildBuildingSystem extends EntitySystem implements SignalListener {

    private Engine engine;

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
                switch (signalComponent.getType()){
                    case BUILDBUILDING:
                        TileComponent tileC = signalEntity.getComponent(TileComponent.class);
                        BuildingComponent buildingC = signalEntity.getComponent(BuildingComponent.class);
                        buildBuilding(tileC, buildingC.getType());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    private void buildBuilding(TileComponent tileC, BuildingType buildingType) {
        Entity building;
        try {
            building = BuildingFactory.createBuilding(buildingType);
        } catch (NoSuchBuildingException e) {
            return;
        }

        engine.addEntity(building);
        tileC.occupy(building);
    }
}