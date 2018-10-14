package main.se.tevej.game.controller.input;

import main.se.tevej.game.controller.input.enums.TKey;

import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.controller.input.libgdx.InputLibgdxFactory;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;
import main.se.tevej.game.controller.input.listeners.OnTimeChangeListener;

public class TimeController implements OnTappedListener {
    private List<OnTimeChangeListener> onChangeListeners;

    public TimeController(TKeyBoard keyBoard) {
        onChangeListeners = new ArrayList<>();
        keyBoard.addTappedListener(this);
        onChangeListeners = new ArrayList<>();
    }

    public void registerOnTimeChange(OnTimeChangeListener listener) {
        onChangeListeners.add(listener);
    }

    @Override
    public void onTapped(TKeyBoard keyBoard, TKey button) {
        switch (button) {
            case KEY_SPACE:
                setMultiplerTo(0);
                break;
            case KEY_1:
                setMultiplerTo(1);
                break;
            case KEY_2:
                setMultiplerTo(2);
                break;
            default:
                break;
        }
    }

    private void setMultiplerTo(float multipler) {
        for (OnTimeChangeListener listener : onChangeListeners) {
            listener.updateTimeMultipler(multipler);
        }
    }
}
