package main.java.se.tevej.game.model.systems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.java.se.tevej.game.model.components.PositionComponent;
import main.java.se.tevej.game.model.components.TileComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingType;
import main.java.se.tevej.game.model.entities.AddToEngineListener;
import main.java.se.tevej.game.model.entities.WorldEntity;
import main.java.se.tevej.game.model.signals.SignalComponent;
import main.java.se.tevej.game.model.signals.SignalListener;
import main.java.se.tevej.game.model.signals.SignalType;
import org.junit.Test;

class DeleteEntitySystemTest {
    public DeleteEntitySystemTest() { super(); }

    @Test
    public void testDeleteBuilding() {

        ////// SETUP THE SYSTEM
        Engine engine = new Engine();
        Signal<Entity> signal = new Signal<>();

        engine.addSystem(new DeleteEntitySystem());
        engine.addSystem(new BuildBuildingSystem());

        SignalListener signalListener = engine.getSystem(BuildBuildingSystem.class);
        signalListener.setSignal(signal);
        signal.add(signalListener.getSignalListener());

        SignalListener signalListener1 = engine.getSystem(DeleteEntitySystem.class);
        signalListener1.setSignal(signal);
        signal.add(signalListener1.getSignalListener());

        Entity worldEntity = new WorldEntity(10, 10, new AddToEngineListener() {
            @Override
            public void addEntityToEngine(Entity entity) {
            }
        });

        // Add world to engine
        WorldComponent worldC = worldEntity.getComponent(WorldComponent.class);
        engine.addEntity(worldEntity);

        // Add building to engine
        Entity entity = new Entity();
        BuildingComponent buildingC = new BuildingComponent(BuildingType.LUMBERMILL);
        entity.add(buildingC);
        PositionComponent positionC = worldEntity.getComponent(WorldComponent.class)
            .getTileAt(2, 3).getComponent(PositionComponent.class);
        entity.add(positionC);
        entity.add(worldC);
        SignalComponent signalC = new SignalComponent(SignalType.BUILD_BUILDING);
        entity.add(signalC);
        signal.dispatch(entity);

        ////// LET THE TESTS BEGIN

        // se att building finns i systemet
        Entity buildingE = engine.getEntitiesFor(Family.one(BuildingComponent.class).get()).first();
        assertNotNull(buildingE);

        // se att både world och building finns
        assertEquals(2, engine.getEntities().size());

        //se att tile har building
        TileComponent tileC = worldC.getTileAt(2, 3).getComponent(TileComponent.class);
        assertEquals(tileC.getOccupier(), buildingE);
        assertNotNull(tileC.getOccupier());

        //----------------delete entity---------------
        buildingE.add(new SignalComponent(SignalType.DELETE_ENTITY));
        signal.dispatch(buildingE);

        // se att tile inte har någon byggnad
        assertNull(tileC.getOccupier());

        // se att det nu bara finns en entity
        assertEquals(1, engine.getEntities().size());

    }
}