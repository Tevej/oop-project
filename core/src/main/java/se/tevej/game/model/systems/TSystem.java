package main.java.se.tevej.game.model.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

import main.java.se.tevej.game.model.signals.SignalListener;

public abstract class TSystem extends EntitySystem implements SignalListener, Listener<Entity> {

    public TSystem() {
        super();
    }

    @Override
    public void setSignal(Signal<Entity> signal) {
    }

    @Override
    public Listener<Entity> getSignalListener() {
        return this;
    }

    @Override
    public void receive(Signal<Entity> signal, Entity object) {

    }
}
