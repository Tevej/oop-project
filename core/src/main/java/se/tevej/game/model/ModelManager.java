package main.java.se.tevej.game.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import main.java.se.tevej.game.model.components.InventoryComponent;
import main.java.se.tevej.game.model.components.NaturalResourceComponent;
import main.java.se.tevej.game.model.components.TileComponent;
import main.java.se.tevej.game.model.components.WorldComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingComponent;
import main.java.se.tevej.game.model.components.buildings.BuildingType;
import main.java.se.tevej.game.model.entities.AddToEngineListener;
import main.java.se.tevej.game.model.entities.BuildingEntity;
import main.java.se.tevej.game.model.entities.InventoryEntity;
import main.java.se.tevej.game.model.entities.NoSuchBuildingException;
import main.java.se.tevej.game.model.entities.WorldEntity;
import main.java.se.tevej.game.model.systems.BuildBuildingSystem;
import main.java.se.tevej.game.model.systems.DeleteEntitySystem;
import main.java.se.tevej.game.model.systems.EntityCreator;
import main.java.se.tevej.game.model.systems.FoodGatheringSystem;
import main.java.se.tevej.game.model.systems.NaturalResourceGatheringSystem;
import main.java.se.tevej.game.model.systems.PaySystem;
import main.java.se.tevej.game.model.systems.PopulationSystem;
import main.java.se.tevej.game.model.systems.SignalHolder;
import main.java.se.tevej.game.model.systems.SpawnNaturalResourceSystem;
import main.java.se.tevej.game.model.systems.TSystem;
import main.java.se.tevej.game.model.systems.TreeGrowthSystem;

/**
 * The ModelManager is responsible for that the model is set up correctly.
 */
@SuppressWarnings("PMD.ExcessiveImports")
public class ModelManager implements AddToEngineListener, SignalHolder, EntityCreator {

    private final Engine engine;
    private final Signal<Entity> signal;

    private int worldWidth;
    private int worldHeight;

    private WorldEntity worldEntity;
    private Entity inventoryEntity;

    /* This constructor is called if there is no previously saved game. It passes null to the
     * entity_parameter which then prompts the constructor to create a new world from scratch.
     */
    public ModelManager(int worldWidth, int worldHeight) {
        this(worldWidth, worldHeight, null);
    }

    public ModelManager(int worldWidth, int worldHeight, List<Entity> entities) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        engine = new Engine();
        signal = new Signal<>();

        worldEntity = new WorldEntity(worldWidth, worldHeight, this);
        addEntityToEngine(worldEntity);

        if (entities == null) {
            initEngineFromStart(worldEntity);
        } else {
            initEngineFromLoadedFile(worldEntity, entities);
        }

        initSystems();
    }

    public void update(float deltaTime) {
        engine.update(deltaTime);
    }

    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public final void addEntityToEngine(Entity entity) {
        engine.addEntity(entity);
    }

    @Override
    public void addEntityListener(EntityListener entityListener) {
        engine.addEntityListener(entityListener);

        //Goes through all entities that already has been added
        for (Entity entity : engine.getEntities()) {
            entityListener.entityAdded(entity);
        }
    }

    public ImmutableArray<Entity> getTiles() {
        return engine.getEntitiesFor(Family.all(TileComponent.class).get());
    }

    public Entity getWorldEntity() {
        return worldEntity;
    }

    public Engine getEngine() {
        return engine;
    }

    public Entity getInventoryEntity() {
        return inventoryEntity;
    }

    private void initEngineFromStart(WorldEntity worldE) {
        createInventoryEntity();
        createStartingHome();
        worldE.createNewWorld();
    }

    private void initEngineFromLoadedFile(WorldEntity worldE, List<Entity> entities) {
        List<Entity> occupierEntities = new ArrayList();

        for (Entity entity : entities) {
            if (entity.getComponent(InventoryComponent.class) != null) {
                this.inventoryEntity = entity;
            }

            if (entity.getComponent(NaturalResourceComponent.class) != null
                || entity.getComponent(BuildingComponent.class) != null) {
                occupierEntities.add(entity);
            }

            engine.addEntity(entity);
        }
        worldE.createWorldFromSave(occupierEntities);
    }

    private void initSystems() {
        addSystem(new BuildBuildingSystem());
        addSystem(new DeleteEntitySystem());
        addSystem(new PaySystem(this));
        addSystem(new NaturalResourceGatheringSystem(this));
        addSystem(new SpawnNaturalResourceSystem());
        addSystem(new TreeGrowthSystem(this, this));
        addSystem(new PopulationSystem());
        addSystem(new FoodGatheringSystem());
    }

    private void addSystem(TSystem system) {
        engine.addSystem(system);
        system.setSignal(signal);
        signal.add(system.getSignalListener());
    }

    private void createStartingHome() {
        Entity homeEntity;
        int homeX = 10;
        int homeY = 10;

        try {
            homeEntity = new BuildingEntity(
                worldEntity.getComponent(WorldComponent.class),
                BuildingType.HOME,
                homeX,
                homeY,
                this
            );
        } catch (NoSuchBuildingException e) {
            homeEntity = new Entity();
            System.out.println("Home is gone");
        }

        worldEntity.getComponent(WorldComponent.class).occupyTile(homeX, homeY, homeEntity);

        addEntityToEngine(homeEntity);
    }

    private void createInventoryEntity() {
        inventoryEntity = new InventoryEntity();
        addEntityToEngine(inventoryEntity);
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

}
