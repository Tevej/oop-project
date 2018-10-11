package main.se.tevej.game.model.ashley;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;

public interface SignalListener {

    void setSignal(Signal<Entity> signal);

    Listener<Entity> getSignalListener();

}
