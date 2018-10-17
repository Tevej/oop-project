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

    // A matrix that represents the world where the values of each tile
    // is the number of trees neighbouring it,
    // or -1 if it's occupied.
    private int[][] numNeighbouringTrees;

    public TreeGrowthSystem(SignalHolder signalHolder) {
        super();
        this.signalHolder = signalHolder;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    private void init() {
        init();
        WorldComponent worldC = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);

        int width = worldC.getWidth();
        int height = worldC.getHeight();

        numNeighbouringTrees = new int[width][height];
        Entity tileE;

        // Init the tree matrix.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tileE = worldC.getTileAt(x, y);
                numNeighbouringTrees[x][y] = getValueForTile(tileE, worldC);
            }
        }
    }

    private int getValueForTile(Entity tileE, WorldComponent worldC) {
        TileComponent tileC;
        int value = 0;
        if (tileE != null) {
            tileC = tileE.getComponent(TileComponent.class);
            if (tileC == null || tileC.isOccupied()) {
                value = -1;
            } else {
                value = getTreeNeighbourCount(tileE, worldC);
            }
        }
        return value;
    }

    private int getTreeNeighbourCount(Entity tileE, WorldComponent worldC) {
        int value = 0;

        Entity[] neighbours = worldC.getTileNeighbours(tileE, true);
        for (Entity neighbour : neighbours) {
            if (neighbour != null) {
                TileComponent tileC = neighbour.getComponent(TileComponent.class);
                value += hasTree(tileC) ? 1 : 0;
            }
        }
        return value;
    }

    private boolean hasTree(TileComponent component) {
        boolean hasTree = false;
        if (component == null) {
            System.out.println("Recieved null tileComponent!");
        } else {
            Entity occupier = component.getOccupier();
            if (occupier != null) {
                NaturalResourceComponent naturalResourceC = occupier
                    .getComponent(NaturalResourceComponent.class);

                hasTree = naturalResourceC != null
                    && naturalResourceC.getType() == ResourceType.WOOD;
            }
        }
        return hasTree;
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
