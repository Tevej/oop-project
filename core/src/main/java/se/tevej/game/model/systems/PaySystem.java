package main.java.se.tevej.game.model.systems;

import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.java.se.tevej.game.model.components.InventoryComponent;
import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingCostUtils;
import main.java.se.tevej.game.model.components.buildings.BuildingType;
import main.java.se.tevej.game.model.resources.NotEnoughResourcesException;
import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.signals.SignalComponent;
import main.java.se.tevej.game.model.signals.SignalType;

public class PaySystem  extends TSystem {

    private Engine engine;
    private SignalHolder signalHolder;

    public PaySystem(SignalHolder signalHolder) {
        super();
        this.signalHolder = signalHolder;
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
        signalEntity.add(new SignalComponent(SignalType.BUILD_BUILDING));
        signalHolder.getSignal().dispatch(signalEntity);
    }

    private boolean isOccupiedLocation(Entity entity) {
        PositionComponent position = entity.getComponent(PositionComponent.class);
        Entity worldEntity = engine.getEntitiesFor(Family.all(WorldComponent.class).get()).first();
        WorldComponent world = worldEntity.getComponent(WorldComponent.class);
        return world.isTileOccupied(position.getX(), position.getY());
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void receive(Signal<Entity> signal, Entity signalEntity) {
        SignalComponent signalComponent = signalEntity.getComponent(SignalComponent.class);
        switch (signalComponent.getType()) {
            case PAY_FOR_CONSTRUCTION:
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
}
