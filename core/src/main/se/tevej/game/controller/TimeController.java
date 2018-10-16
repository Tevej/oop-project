package main.se.tevej.game.controller;

import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.controller.input.base.OnTappedListener;
import main.se.tevej.game.controller.input.base.TKey;
import main.se.tevej.game.controller.input.base.TKeyBoard;

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
                setTimeMultiplier(0);
                break;
            case KEY_1:
                setTimeMultiplier(1);
                break;
            case KEY_2:
                setTimeMultiplier(2);
                break;
            default:
                break;
        }
    }

    private void setTimeMultiplier(float multiplier) {
        for (OnTimeChangeListener listener : onChangeListeners) {
            listener.updateTimeMultipler(multiplier);
        }
    }
}
