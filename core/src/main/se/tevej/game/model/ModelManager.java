package main.se.tevej.game.model;

import java.util.Arrays;
import java.util.List;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.entities.AddToEngineListener;
import main.se.tevej.game.model.entities.BuildingEntity;
import main.se.tevej.game.model.entities.InventoryEntity;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.model.exceptions.NoSuchBuildingException;
import main.se.tevej.game.model.systems.BuildBuildingSystem;
import main.se.tevej.game.model.systems.DeleteEntitySystem;
import main.se.tevej.game.model.systems.NaturalResourceGatheringSystem;
import main.se.tevej.game.model.systems.PaySystem;
import main.se.tevej.game.model.systems.SignalHolder;

public class ModelManager implements AddToEngineListener, SignalHolder {

    private final Engine engine;
    private final Signal<Entity> signal;

    private int worldWidth;
    private int worldHeight;

    private WorldEntity worldEntity;
    private Entity inventoryEntity;

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

    public void addEntityListener(EntityListener entityListener) {
        engine.addEntityListener(entityListener);

        //Goes through all entities that already has been added
        for (Entity entity : engine.getEntities()) {
            entityListener.entityAdded(entity);
        }
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
        for (Entity entity : entities) {
            if (entity.getComponent(InventoryComponent.class) != null) {
                this.inventoryEntity = entity;
            }

            engine.addEntity(entity);
        }

        ImmutableArray<Entity> occupierArray = engine.getEntitiesFor(Family.all(
            NaturalResourceComponent.class, BuildingComponent.class).get());
        List<Entity> occupierEntities = Arrays.asList(occupierArray.toArray());
        worldE.createWorldFromSave(occupierEntities);
    }

    private void initSystems() {
        engine.addSystem(new BuildBuildingSystem());
        engine.addSystem(new DeleteEntitySystem());
        engine.addSystem(new PaySystem(this));
        engine.addSystem(new NaturalResourceGatheringSystem(this));

        engine.getSystems().forEach(entitySystem -> {
            if (entitySystem instanceof SignalListener) {
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(signal);
                signal.add(signalListener.getSignalListener());
            }
        });
    }

    private void createStartingHome() {
        Entity homeEntity;
        int homeX = 10;
        int homeY = 10;

        try {
            homeEntity = new BuildingEntity(BuildingType.HOME, homeX, homeY);
        } catch (NoSuchBuildingException e) {
            homeEntity = new Entity();
            System.out.println("Home is gone");
        }

        Entity tileAt = worldEntity.getComponent(WorldComponent.class).getTileAt(homeX, homeY);
        tileAt.getComponent(TileComponent.class).occupy(homeEntity);

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
