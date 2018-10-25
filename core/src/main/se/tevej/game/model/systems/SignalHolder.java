package main.se.tevej.game.model.systems;

import com.badlogic.ashley.signals.Signal;

/**
 * An inteface for modelManager so that we can initialize the signaling( observer ) pattern.
 */
public interface SignalHolder {
    Signal getSignal();
}
