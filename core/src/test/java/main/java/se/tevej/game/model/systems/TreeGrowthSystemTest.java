package main.java.se.tevej.game.model.systems;

import static org.junit.Assert.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.signals.Signal;

import main.java.se.tevej.game.model.components.TileComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.entities.AddToEngineListener;
import main.java.se.tevej.game.model.entities.NaturalResourceEntity;
import main.java.se.tevej.game.model.entities.WorldEntity;
import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.resources.ResourceType;
import org.junit.Test;

class TreeGrowthSystemTest {

    public TreeGrowthSystemTest() { super(); }

    @Test
    public void testTreeGrowthSystem() {

        // Setup

        Engine engine = new Engine();

        Entity worldEntity = new WorldEntity(10, 10, new AddToEngineListener() {
            @Override
            public void addEntityToEngine(Entity entity) {
            }
        });

        engine.addEntity(worldEntity);

        // Test stuff

        TreeGrowthSystem system = new TreeGrowthSystem(
            new SignalHolder() {
                @Override
                public Signal getSignal() {
                    return null;
                }
            },
            new EntityCreator() {
                @Override
                public void addEntityListener(EntityListener listener) {

                }
            });

        WorldComponent worldC = worldEntity.getComponent(WorldComponent.class);
        spawnTreeAt(6, 5, worldC);
        assertEquals(system.getValueForTile(worldC.getTileAt(5, 5), worldC), 1, 0);
        spawnTreeAt(5, 6, worldC);
        assertEquals(system.getValueForTile(worldC.getTileAt(5, 5), worldC), 2, 0);
        spawnTreeAt(4, 4, worldC);
        assertEquals(system.getValueForTile(worldC.getTileAt(5, 5), worldC), 3, 0);
    }

    private void spawnTreeAt(int x, int y, WorldComponent worldC) {
        worldC.getTileAt(x, y).getComponent(TileComponent.class).occupy(getTree(x, y));
    }

    private Entity getTree(int x, int y) {
        return new NaturalResourceEntity(x, y, new Resource(1, ResourceType.WOOD));
    }
}