package main.se.tevej.game.model.systems;

import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.exceptions.NotEnoughResourcesException;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.utils.BuildingCostUtils;
import main.se.tevej.game.model.utils.Resource;

public class PaySystem extends EntitySystem implements SignalListener {

    private Engine engine;
    private EntityManager em;

    public PaySystem(EntityManager em) {
        super();
        this.em = em;
    }


    private void pay(Entity entity) {
        BuildingType type = entity.getComponent(BuildingComponent.class).getType();
        List<Resource> cost = BuildingCostUtils.getCostOfBuilding(type);

        InventoryComponent inventoryC = engine.getEntitiesFor(
            Family.all(InventoryComponent.class).get())
            .first().getComponent(InventoryComponent.class);
        try {
            inventoryC.removeFromInventory(cost);
            sendBuildSignal(entity);
        } catch (NotEnoughResourcesException e) {
            System.out.println("Deselect please " + e);
        }
    }

    private void sendBuildSignal(Entity signalEntity) {
        signalEntity.remove(SignalComponent.class);
        signalEntity.add(new SignalComponent(SignalType.BUILDBUILDING));
        em.getSignal().dispatch(signalEntity);
    }

    private boolean isOccupiedLocation(Entity entity) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        WorldComponent world = engine.getEntitiesFor(Family.all(WorldComponent.class).get()).first()
            .getComponent(WorldComponent.class);
        TileComponent tileComponent = world.getTileAt(position.getX(), position.getY())
            .getComponent(TileComponent.class);
        return tileComponent.isOccupied();

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
                    case PAYFORCONSTRUCTION:
                        if (isOccupiedLocation(signalEntity)) {
                            break;
                        } else {
                            pay(signalEntity);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
