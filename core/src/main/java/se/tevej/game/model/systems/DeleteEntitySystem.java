package main.java.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.signals.SignalComponent;

public class DeleteEntitySystem extends TSystem {

    private Engine engine;

    public DeleteEntitySystem() {
        super();
    }

    private void deleteEntity(Entity signalEntity) {
        WorldComponent worldC = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);
        PositionComponent pc = signalEntity.getComponent(PositionComponent.class);
        worldC.removeOccupier(pc.getX(), pc.getY());
        engine.removeEntity(signalEntity);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void receive(Signal<Entity> signal, Entity signalEntity) {
        SignalComponent signalComponent = signalEntity.getComponent(SignalComponent.class);
        switch (signalComponent.getType()) {
            case DELETE_ENTITY:
                deleteEntity(signalEntity);
                break;
            default:
                break;
        }
    }
}
