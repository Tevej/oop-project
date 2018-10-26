package main.java.se.tevej.game.model.signals;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

/**
 * A specification for all systems that listens for signals.
 */
public interface SignalListener {

    void setSignal(Signal<Entity> signal);

    Listener<Entity> getSignalListener();

}
