package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;

public class DeleteEntitySystem extends EntitySystem implements SignalListener {

    private Engine engine;

    public DeleteEntitySystem() {
        super();
    }

    private void deleteEntity(Entity signalEntity) {
        WorldComponent wc = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);
        PositionComponent pc = signalEntity.getComponent(PositionComponent.class);
        Entity tile = wc.getTileAt((int) pc.getX(), (int) pc.getY());
        TileComponent tc = tile.getComponent(TileComponent.class);
        tc.occupy(null);
        engine.removeEntity(signalEntity);
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
                    case DELETEENTITY:
                        deleteEntity(signalEntity);
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
