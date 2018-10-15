package main.se.tevej.game.model;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.ashley.SignalListener;
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

    private Entity worldEntity;
    private Entity inventoryEntity;

    public ModelManager(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        engine = new Engine();
        signal = new Signal<>();
        initSystems();
        initStartingEntities(worldWidth, worldHeight);
    }

    public void update(float deltaTime) {
        engine.update(deltaTime);
    }

    @Override
    public Signal getSignal() {
        return signal;
    }

    @Override
    public void addEntityToEngine(Entity entity) {
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

    public Entity getInventoryEntity() {
        return inventoryEntity;
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

    private void initStartingEntities(int worldWidth, int worldHeight) {
        createWorldEntity(worldWidth, worldHeight);
        createInventoryEntity();
        createStartingHome();
    }

    private void createStartingHome() {
        Entity homeEntity;

        try {
            homeEntity = new BuildingEntity(BuildingType.HOME,10,10);
        } catch (NoSuchBuildingException e) {
            homeEntity = new Entity();
            System.out.println("Home is gone");
        }

        addEntityToEngine(homeEntity);
    }

    private void createInventoryEntity() {
        inventoryEntity = new InventoryEntity();
        addEntityToEngine(inventoryEntity);
    }

    private void createWorldEntity(int worldWidth, int worldHeight) {
        worldEntity = new WorldEntity(worldWidth, worldHeight, this);
        addEntityToEngine(worldEntity);
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
