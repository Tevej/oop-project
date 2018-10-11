package main.se.tevej.game.model.ashley;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.systems.BuildBuildingSystem;
import main.se.tevej.game.model.systems.DeleteEntitySystem;
import main.se.tevej.game.model.systems.NaturalResourceGatheringSystem;
import main.se.tevej.game.model.systems.PaySystem;

public class EntityManager {

    private final Engine ENGINE;
    private final Signal<Entity> SIGNAL;

    public EntityManager() {
        ENGINE = new Engine();
        SIGNAL = new Signal<>();
        init();
    }

    private void init() {
        //Add systems here
        //ENGINE.addSystem(new RenderingSystem());
        ENGINE.addSystem(new BuildBuildingSystem());
        ENGINE.addSystem(new DeleteEntitySystem());
        ENGINE.addSystem(new PaySystem(this));
        ENGINE.addSystem(new NaturalResourceGatheringSystem(this));

        ENGINE.getSystems().forEach(entitySystem -> {
            if (entitySystem instanceof SignalListener) {
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(SIGNAL);
                SIGNAL.add(signalListener.getSignalListener());
            }
        });
    }

    public void update(float deltaTime) {
        ENGINE.update(deltaTime);
    }

    public Entity createEntity() {
        return new Entity();
    }

    public Signal getSignal() {
        return SIGNAL;
    }

    public void addEntityToEngine(Entity entity) {
        ENGINE.addEntity(entity);
    }

    public void addEntityListener(EntityListener entityListener) {
        ENGINE.addEntityListener(entityListener);
    }

}
