package systems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.signals.SignalComponent;
import main.se.tevej.game.model.signals.SignalListener;
import main.se.tevej.game.model.signals.SignalType;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.components.buildings.GathererComponent;
import main.se.tevej.game.model.entities.AddToEngineListener;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.model.systems.BuildBuildingSystem;
import org.junit.Test;

public class TestBuildBuildingSystem {

    public TestBuildBuildingSystem() {
        super();
    }

    @Test
    public void testBuildBuilding() {

        ////// SETUP THE SYSTEM
        Engine engine = new Engine();
        Signal<Entity> signal = new Signal<>();

        engine.addSystem(new BuildBuildingSystem());
        SignalListener signalListener = engine.getSystem(BuildBuildingSystem.class);
        signalListener.setSignal(signal);
        signal.add(signalListener.getSignalListener());

        Entity worldEntity = new WorldEntity(10, 10, new AddToEngineListener() {
            @Override
            public void addEntityToEngine(Entity entity) {
            }
        });
        engine.addEntity(worldEntity);
        Entity entity = new Entity();
        BuildingComponent buildingC = new BuildingComponent(BuildingType.LUMBERMILL);
        entity.add(buildingC);

        PositionComponent positionC = worldEntity.getComponent(WorldComponent.class)
            .getTileAt(2, 3).getComponent(PositionComponent.class);
        entity.add(positionC);

        WorldComponent worldC = worldEntity.getComponent(WorldComponent.class);
        entity.add(worldC);

        SignalComponent signalC = new SignalComponent(SignalType.BUILDBUILDING);
        entity.add(signalC);
        signal.dispatch(entity);

        ////// LET THE TESTS BEGIN

        // se att building finns i systemet
        Entity buildingE = engine.getEntitiesFor(Family.all(BuildingComponent.class,
            GathererComponent.class).get()).first();
        assertNotNull(buildingE);

        //se att tile har building
        assertEquals(worldC.getTileOccupier(2, 3), buildingE);

        //se att building har rätt position
        PositionComponent buildingPositionC = buildingE.getComponent(PositionComponent.class);
        assertEquals(2, buildingPositionC.getX());
        assertEquals(3, buildingPositionC.getY());

    }
}
