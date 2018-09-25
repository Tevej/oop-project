package se.tevej.game.model.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SignalComponent implements Component, Pool.Poolable {

    public SignalType signalType;

    @Override
    public void reset() {
        signalType = null;
    }
}
