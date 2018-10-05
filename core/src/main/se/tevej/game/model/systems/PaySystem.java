package main.se.tevej.game.model.systems;

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
import main.se.tevej.game.model.components.CostComponent;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

import java.util.List;

public class PaySystem extends EntitySystem implements SignalListener{

    private Engine engine;
    private EntityManager em;

    public PaySystem(EntityManager em) {
        this.em = em;
    }


    private void pay(Entity entity){
        BuildingType type = entity.getComponent(BuildingComponent.class).getType();
        List<Resource> cost = CostComponent.getCostOfBuilding(type);

        InventoryComponent iC = engine.getEntitiesFor(Family.all(InventoryComponent.class).get())
                    .first().getComponent(InventoryComponent.class);
        try {
            iC.removeFromInventory(cost);
            sendBuildSignal(entity);
        } catch (NotEnoughResourcesException e) {
            System.out.println("Deselect please "+ e );
        }
    }

    private void sendBuildSignal(Entity signalEntity){
        signalEntity.remove(SignalComponent.class);
        signalEntity.add(new SignalComponent(SignalType.BUILDBUILDING));
        em.getSignal().dispatch(signalEntity);
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
                switch (signalComponent.getType()){
                    case PAYFORCONSTRUCTION:
                        pay(signalEntity);
                        break;
                    default:
                        break;
                }
            }
        };
    }
}
