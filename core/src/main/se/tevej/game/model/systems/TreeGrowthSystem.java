package main.se.tevej.game.model.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.utils.ResourceType;

public class TreeGrowthSystem extends EntitySystem {
    private Engine engine;
    // The number of trees to spawn
    private final double treeSpawnRate = 1.0 / (250);
    private final float treeSpawnTime = 1f;
    private float treeSpawnProgress;

    public TreeGrowthSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    private void update(float deltaTime) {
        List<Entity> availableTiles = getAvailableTreeSpawnLocations(getTreeEntities());

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
            treeNeighbours = getTileNeighbours(treeE);
        }
    }
}
