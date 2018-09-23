package se.tevej.game.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.signals.Signal;

public class EntityManager {

    public EntityManager(){
        ENGINE = new PooledEngine();
        SIGNAL = new Signal<>();
    }

    private final PooledEngine ENGINE;
    private final Signal<Entity> SIGNAL;

    public void init(){
        //Add systems here
        //ENGINE.addSystem(new RenderingSystem());

        ENGINE.getSystems().forEach(entitySystem -> {
            if(entitySystem instanceof SignalListener){
                SignalListener signalListener = (SignalListener) entitySystem;
                signalListener.setSignal(SIGNAL);
                SIGNAL.add(signalListener.getSignalListener());
            }
        });
    }

    public void update(float deltaTime){
        ENGINE.update(deltaTime);
    }

    public Entity createEntity(){
        return ENGINE.createEntity();
    }

    public <C extends Component> C createComponent(Class<C> componentType){
        return ENGINE.createComponent(componentType);
    }

    public void addEntityToEngine(Entity entity){
        ENGINE.addEntity(entity);
    }

}
