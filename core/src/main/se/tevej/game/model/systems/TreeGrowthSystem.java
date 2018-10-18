package main.se.tevej.game.model.systems;

import java.util.Random;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.utils.ResourceType;

public class TreeGrowthSystem extends EntitySystem implements EntityListener {
    private Engine engine;
    private SignalHolder signalHolder;

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final double chanceMultiplier = 1.0 / (20 * 1000);

    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final double baseSpawnChance = 1.0 / (25 * 1000 * 1000);

    // A matrix that represents the world where the values of8 each tile
    // is the number of trees neighbouring it,
    // or -1 if it's occupied.
    private int[][] treesMap;
    private EntityCreator entityCreator;

    public TreeGrowthSystem(SignalHolder signalHolder, EntityCreator entityCreator) {
        super();
        this.signalHolder = signalHolder;
        this.entityCreator = entityCreator;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
        init();
    }

    private void init() {
        WorldComponent worldC = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);

        int width = worldC.getWidth();
        int height = worldC.getHeight();

        treesMap = new int[width][height];
        Entity tileE;

        // Init the tree matrix.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tileE = worldC.getTileAt(x, y);
                treesMap[x][y] = getValueForTile(tileE, worldC);
            }
        }

        // Now register on Entity created and make sure to keep the matrix up to date.
        entityCreator.addEntityListener(this);
    }

    @Override
    public void update(float deltaTime) {
        Random rand = new Random();
        int value;
        double spawnChance;
        double currValue;

        for (int x = 0; x < treesMap.length; x++) {
            for (int y = 0; y < treesMap[x].length; y++) {
                value = treesMap[x][y];
                if (value > -1) {
                    spawnChance = baseSpawnChance + ((value * chanceMultiplier) * deltaTime);
                    currValue = rand.nextDouble();
                    if (currValue < spawnChance) {
                        spawnTreeAt(x, y);
                    }
                }
            }
        }
    }

    private void spawnTreeAt(int x, int y) {
        Entity signalEntity = SpawnNaturalResourceSystem.getSignalEntity(
            ResourceType.WOOD, 1000, x, y);
        signalHolder.getSignal().dispatch(signalEntity);
    }

    private void updateValuesAroundTile(int x, int y, Entity tileE, WorldComponent worldC) {
        treesMap[x][y] = getValueForTile(tileE, worldC);
        Entity[] tileNeighbours = worldC.getTileNeighbours(tileE, true);
        PositionComponent posC;
        for (Entity neighbour : tileNeighbours) {
            if (neighbour != null) {
                posC = neighbour.getComponent(PositionComponent.class);

                int value = getValueForTile(neighbour, worldC);
                treesMap[posC.getX()][posC.getY()] = value;
            }
        }
    }

    public int getValueForTile(Entity tileE, WorldComponent worldC) {
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
    public void entityAdded(Entity entity) {
        entityChanged(entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        entityChanged(entity);
    }

    private void entityChanged(Entity entity) {
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        if (posC != null) {
            updateTreeCountAt(posC.getX(), posC.getY());
        }
    }

    private void updateTreeCountAt(int x, int y) {
        WorldComponent worldC = engine.getEntitiesFor(Family.all(WorldComponent.class).get())
            .first().getComponent(WorldComponent.class);
        Entity tileE = worldC.getTileAt(x, y);
        if (tileE != null) {
            updateValuesAroundTile(x, y, tileE, worldC);
        }
    }
}
