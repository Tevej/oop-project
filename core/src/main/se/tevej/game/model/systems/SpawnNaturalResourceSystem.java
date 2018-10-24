package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.entities.NaturalResourceEntity;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;
import main.se.tevej.game.model.signals.SignalComponent;
import main.se.tevej.game.model.signals.SignalType;

public class SpawnNaturalResourceSystem extends TSystem {

    private Engine engine;

    public SpawnNaturalResourceSystem() {
        super();
    }

    public static Entity getSignalEntity(ResourceType type, float amount, int x, int y) {
        Entity signalEntity = new Entity();
        signalEntity.add(new PositionComponent(x, y));
        Resource resource = new Resource(amount, type);
        signalEntity.add(new NaturalResourceComponent(resource));
        signalEntity.add(new SignalComponent(SignalType.SPAWNENTITY));
        return signalEntity;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    private void spawnNaturalResource(Entity entity) {
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        WorldComponent worldC = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);

        Entity tileE = worldC.getTileAt(posC.getX(), posC.getY());

        if (tileE != null) {
            TileComponent tileC = tileE.getComponent(TileComponent.class);

            NaturalResourceComponent naturalResourceC = entity
                .getComponent(NaturalResourceComponent.class);

            Resource resource = new Resource(naturalResourceC.getAmountLeft(),
                naturalResourceC.getType());

            if (tileC.isOccupied() == false) {
                Entity newEntity = new NaturalResourceEntity(posC.getX(), posC.getY(), resource);
                engine.addEntity(newEntity);
                tileC.occupy(newEntity);
            }
        }
    }

    @Override
    public void receive(Signal<Entity> signal, Entity signalEntity) {
        SignalComponent signalComponent = signalEntity.getComponent(SignalComponent.class);
        switch (signalComponent.getType()) {
            case SPAWNENTITY:
                spawnNaturalResource(signalEntity);
                break;
            default:
                break;
        }
    }
}
