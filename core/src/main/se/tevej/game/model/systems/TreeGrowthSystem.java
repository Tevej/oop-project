package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.utils.ResourceType;

public class TreeGrowthSystem extends EntitySystem {
    private Engine engine;
    private SignalHolder signalHolder;

    // The number of trees to spawn
    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final double treeSpawnRate = 1.0 / (25 * 2 * 1000);
    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final float treeSpawnTime = 1f;
    private float treeSpawnProgress;

    public TreeGrowthSystem(SignalHolder signalHolder) {
        super();
        this.signalHolder = signalHolder;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        treeSpawnProgress += deltaTime;

        if (treeSpawnProgress >= treeSpawnTime) {
            List<Entity> availableTiles = getAvailableTreeSpawnLocations(getTreeEntities());

            Random rand = new Random();
            PositionComponent posC;
            for (Entity entity : availableTiles) {
                posC = entity.getComponent(PositionComponent.class);
                if (rand.nextFloat() <= treeSpawnRate) {
                    System.out.println("Tree grew at " + posC.getX() + ", " + posC.getY());

                    Entity signalEntity = SpawnNaturalResourceSystem.getSignalEntity(
                        ResourceType.WOOD, 1000, posC.getX(), posC.getY());
                    signalHolder.getSignal().dispatch(signalEntity);
                }
            }

            treeSpawnProgress = 0;
        }
    }

    private List<Entity> getTreeEntities() {
        ImmutableArray<Entity> naturalResources = engine.getEntitiesFor(
            Family.all(NaturalResourceComponent.class).get()
        );

        List<Entity> treeEntities = new ArrayList<>();

        NaturalResourceComponent naturalResourceC;
        for (Entity entity : naturalResources) {
            naturalResourceC = entity.getComponent(NaturalResourceComponent.class);
            if (naturalResourceC.getType() == ResourceType.WOOD) {
                treeEntities.add(entity);
            }
        }

        return treeEntities;
    }

    private List<Entity> getAvailableTreeSpawnLocations(List<Entity> treeEntities) {
        List<Entity> availableTiles = new ArrayList();
        List<Entity> treeNeighbours;

        for (Entity treeE : treeEntities) {
            treeNeighbours = getAvailableNeighbours(treeE);
            for (Entity neighbour : treeNeighbours) {
                availableTiles.add(neighbour);
            }
        }

        return availableTiles;
    }

    private List<Entity> getAvailableNeighbours(Entity treeE) {
        List<Entity> availableTiles = new ArrayList<>();

        Entity worldE = engine.getEntitiesFor(Family.all(WorldComponent.class).get()).first();
        WorldComponent worldC = worldE.getComponent(WorldComponent.class);
        TileComponent tileC;
        Entity[] treeNeighbours = worldC.getTileNeighbours(treeE, true);

        for (Entity neighbour : treeNeighbours) {
            if (neighbour == null) {
                continue;
            }

            tileC = neighbour.getComponent(TileComponent.class);
            if (tileC.isOccupied() == false) {
                availableTiles.add(neighbour);
            }
        }
        return availableTiles;
    }
}
