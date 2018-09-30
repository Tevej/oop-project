package main.se.tevej.game.model.ashley;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Signal;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.factories.WorldFactory;
import main.se.tevej.game.model.systems.BuildBuildingSystem;

public class EntityManager {

    private final Engine ENGINE;
    private final Signal<Entity> SIGNAL;

    public EntityManager(){
        ENGINE = new Engine();
        SIGNAL = new Signal<>();
        init();
    }

    private void init() {
        //Add systems here
        //ENGINE.addSystem(new RenderingSystem());
        ENGINE.addSystem(new BuildBuildingSystem());

        ENGINE.getSystems().forEach(entitySystem -> {
            if(entitySystem instanceof SignalListener){
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(SIGNAL);
                SIGNAL.add(signalListener.getSignalListener());
            }
        });
    }

    public void update(float deltaTime){
        ENGINE.update(deltaTime);
    }

    public Entity createEntity(){
        return new Entity();
    }

    public Signal getSignal() {
        return SIGNAL;
    }

    public void addEntityToEngine(Entity entity){
        ENGINE.addEntity(entity);
    }

    public void addEntityListener(EntityListener entityListener){
        ENGINE.addEntityListener(entityListener);
    }

}
