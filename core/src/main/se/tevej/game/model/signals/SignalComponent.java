package main.se.tevej.game.model.signals;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * When a signal is dispatched to all the system the signalComponent helps identify the
 * type of signal.
 */
public class SignalComponent implements Component, Pool.Poolable {

    private SignalType signalType;

    public SignalComponent(SignalType type) {
        this.signalType = type;
    }

    @Override
    public void reset() {
        signalType = null;
    }

    public SignalType getType() {
        return signalType;
    }
}
