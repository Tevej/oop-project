package systems;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.signals.SignalListener;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.entities.AddToEngineListener;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.model.resources.ResourceType;
import main.se.tevej.game.model.systems.SpawnNaturalResourceSystem;
import org.junit.Test;

public class TestSpawnNaturalResourceSystem {
    public TestSpawnNaturalResourceSystem() { super(); }

    @Test
    public void testSpawnNaturalResource() {

        // Setup

        Engine engine = new Engine();
        Signal<Entity> signal = new Signal<>();
        engine.addSystem(new SpawnNaturalResourceSystem());

        SignalListener signalListener = engine.getSystem(SpawnNaturalResourceSystem.class);
        signalListener.setSignal(signal);
        signal.add(signalListener.getSignalListener());

        Entity worldEntity = new WorldEntity(10, 10, new AddToEngineListener() {
            @Override
            public void addEntityToEngine(Entity entity) {
            }
        });

        engine.addEntity(worldEntity);

        // Test stuff
        int posX = 2;
        int posY = 2;
        int amount = 42;
        ResourceType type = ResourceType.STONE;
        WorldComponent worldC = worldEntity.getComponent(WorldComponent.class);

        assertFalse(worldC.getTileAt(posX, posY).getComponent(TileComponent.class).isOccupied());
        Entity signalEntity = SpawnNaturalResourceSystem.getSignalEntity(type, amount, posX, posY);
        signal.dispatch(signalEntity);
        TileComponent tileC = worldC.getTileAt(posX, posY).getComponent(TileComponent.class);
        assertTrue(tileC.isOccupied());
        Entity occupier = tileC.getOccupier();
        NaturalResourceComponent naturalResourceC = occupier.getComponent(NaturalResourceComponent.class);
        assertTrue(naturalResourceC.getType() == type);
        assertTrue(naturalResourceC.getAmountLeft() == amount);
    }
}