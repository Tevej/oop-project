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

    private final Engine engine;
    private final Signal<Entity> signal;

    public EntityManager() {
        engine = new Engine();
        signal = new Signal<>();
        init();
    }

    private void init() {
        //Add systems here
        //engine.addSystem(new RenderingSystem());
        engine.addSystem(new BuildBuildingSystem());
        engine.addSystem(new DeleteEntitySystem());
        engine.addSystem(new PaySystem(this));
        engine.addSystem(new NaturalResourceGatheringSystem(this));

        engine.getSystems().forEach(entitySystem -> {
            if (entitySystem instanceof SignalListener) {
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(signal);
                signal.add(signalListener.getSignalListener());
            }
        });
    }

    public void update(float deltaTime) {
        engine.update(deltaTime);
    }

    public Entity createEntity() {
        return new Entity();
    }

    public Signal getSignal() {
        return signal;
    }

    public void addEntityToEngine(Entity entity) {
        engine.addEntity(entity);
    }

    public void addEntityListener(EntityListener entityListener) {
        engine.addEntityListener(entityListener);
    }

}
