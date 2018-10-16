package systems;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.entities.AddToEngineListener;
import main.se.tevej.game.model.entities.InventoryEntity;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.model.systems.BuildBuildingSystem;
import main.se.tevej.game.model.systems.PaySystem;
import main.se.tevej.game.model.systems.SignalHolder;
import main.se.tevej.game.model.utils.ResourceType;
import org.junit.Test;

public class TestPaySystem {

    public TestPaySystem() {
        super();
    }

    @Test
    public void testPayment() {

        ////// SETUP THE SYSTEM
        Engine engine = new Engine();
        Signal<Entity> signal = new Signal<>();

        engine.addSystem(new BuildBuildingSystem());
        engine.addSystem(new PaySystem(new SignalHolder() {
            @Override
            public Signal getSignal() {
                return signal;
            }
        }));
        //Add buildsystem to engine
        SignalListener signalListener = engine.getSystem(BuildBuildingSystem.class);
        signalListener.setSignal(signal);
        signal.add(signalListener.getSignalListener());

        //Add paysystem to engine
        SignalListener signalListener1 = engine.getSystem(PaySystem.class);
        signalListener1.setSignal(signal);
        signal.add(signalListener1.getSignalListener());

        Entity worldEntity = new WorldEntity(10, 10, new AddToEngineListener() {
            @Override
            public void addEntityToEngine(Entity entity) {
            }
        }, true);
        clearMap(worldEntity);
        engine.addEntity(worldEntity);

        Entity inventoryE = new InventoryEntity();
        engine.addEntity(inventoryE);

        ////// LET THE TESTS BEGIN

        testInventoryValues(engine, 1000, 1000, 1000);

        payAndBuild(worldEntity, signal, BuildingType.HOME, 1, 2);
        testInventoryValues(engine, 1000, 900, 900);

        payAndBuild(worldEntity, signal, BuildingType.LUMBERMILL, 2, 3);
        testInventoryValues(engine, 800, 900, 600);

        payAndBuild(worldEntity, signal, BuildingType.QUARRY, 3, 4);
        testInventoryValues(engine, 600, 800, 500);

        payAndBuild(worldEntity, signal, BuildingType.PUMP, 4, 5);
        testInventoryValues(engine, 200, 800, 400);
    }


    //These method are supposed to make the test more readable since there is much repetition
    public void payAndBuild(Entity worldEntity, Signal signal, BuildingType type, int x, int y ) {
        Entity entity = new Entity();
        BuildingComponent buildingC = new BuildingComponent(type);
        entity.add(buildingC);

        PositionComponent positionC = worldEntity.getComponent(WorldComponent.class)
            .getTileAt(x, y).getComponent(PositionComponent.class);
        entity.add(positionC);

        WorldComponent worldC = worldEntity.getComponent(WorldComponent.class);
        entity.add(worldC);

        SignalComponent signalC = new SignalComponent(SignalType.PAYFORCONSTRUCTION);
        entity.add(signalC);
        signal.dispatch(entity);

    }

    public void testInventoryValues(Engine engine,
                                    double stone, double water, double wood) {
        InventoryComponent inventoryC = engine.getEntitiesFor(
            Family.all(InventoryComponent.class).get())
            .first().getComponent(InventoryComponent.class);

        assertEquals(stone,
            inventoryC.getAmountOfResource(ResourceType.STONE), 0);

        assertEquals(water,
            inventoryC.getAmountOfResource(ResourceType.WATER), 0);

        assertEquals(wood,
            inventoryC.getAmountOfResource(ResourceType.WOOD), 0);
    }

    public void clearMap(Entity world) {
        WorldComponent wc = world.getComponent(WorldComponent.class);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                wc.getTileAt(i,j).getComponent(TileComponent.class).occupy(null);
            }
        }
    }
}
