package main.se.tevej.game.model.ashley;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.signals.Signal;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.factories.WorldFactory;
import main.se.tevej.game.model.systems.BuildBuildingSystem;

public class EntityManager {

    private final Engine ENGINE;
    private final Signal<Entity> SIGNAL;

    public EntityManager(){
        ENGINE = new Engine();
        SIGNAL = new Signal<>();
    }

    public void init(){
        //Add systems here
        //ENGINE.addSystem(new RenderingSystem());
        ENGINE.addSystem(new BuildBuildingSystem());

        ENGINE.getSystems().forEach(entitySystem -> {
            if(entitySystem instanceof SignalListener){
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(SIGNAL);
                SIGNAL.add(signalListener.getSignalListener());
            }
        });

        // createWorldEntity also calls CreateTileEntity and adds it to the engine, this might want to be done separately
        Entity worldEntity = WorldFactory.createWorldEntity(100, 100, this);
        addEntityToEngine(worldEntity);

        Entity buildHomeBuilding = new Entity();
        buildHomeBuilding.add(new HomeComponent());
        buildHomeBuilding.add(worldEntity.getComponent(WorldComponent.class).getTileAt(50, 50).getComponent(TileComponent.class));
        buildHomeBuilding.add(new SignalComponent(SignalType.BUILDBUILDING));
        SIGNAL.dispatch(buildHomeBuilding);
    }

    public void update(float deltaTime){
        ENGINE.update(deltaTime);
    }

    public Entity createEntity(){
        return new Entity();
    }

    public void addEntityToEngine(Entity entity){
        ENGINE.addEntity(entity);
    }

    public void addEntityListener(EntityListener entityListener){
        ENGINE.addEntityListener(entityListener);
    }

}
