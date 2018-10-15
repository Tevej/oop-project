package main.se.tevej.game.model;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.signals.Signal;

import main.se.tevej.game.model.ashley.SignalListener;
import main.se.tevej.game.model.components.InventoryComponent;
import main.se.tevej.game.model.entities.WorldEntity;
import main.se.tevej.game.model.systems.BuildBuildingSystem;
import main.se.tevej.game.model.systems.DeleteEntitySystem;
import main.se.tevej.game.model.systems.NaturalResourceGatheringSystem;
import main.se.tevej.game.model.systems.PaySystem;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.utils.Manager;
import main.se.tevej.game.utils.Options;

public class ModelManager implements Manager {

    private final Engine engine;
    private final Signal<Entity> signal;

    private final Options options;

    private Entity worldEntity;
    private Entity inventoryEntity;

    public ModelManager(Options options) {
        this.options = options;

        engine = new Engine();
        signal = new Signal<>();
    }

    @Override
    public void update(float deltaTime) {
        engine.update(deltaTime);
    }

    @Override
    public void init() {
        initSystems();
        initStartingEntities();
    }

    public Signal getSignal() {
        return signal;
    }

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

    private void initStartingEntities() {
        worldEntity = createWorldEntity(options.getWorldWidth(), options.getWorldHeight());
        inventoryEntity = createInventoryEntity();

        addEntityToEngine(worldEntity);
        addEntityToEngine(inventoryEntity);
    }

    private Entity createInventoryEntity() {
        Entity inventoryEntity = new Entity();
        InventoryComponent inventoryC = new InventoryComponent();
        inventoryEntity.add(inventoryC);
        inventoryC.addResource(new Resource(1000, ResourceType.WOOD));
        inventoryC.addResource(new Resource(1000, ResourceType.WATER));
        inventoryC.addResource(new Resource(1000, ResourceType.STONE));
        return inventoryEntity;
    }

    private Entity createWorldEntity(int worldWidth, int worldHeight) {
        return new WorldEntity(worldWidth, worldHeight, this);
    }

}
