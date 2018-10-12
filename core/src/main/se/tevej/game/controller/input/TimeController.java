package main.se.tevej.game.controller.input;

import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTappedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTimeChangeListener;

import java.util.ArrayList;
import java.util.List;

public class TimeController implements OnTappedListener {
    private List<OnTimeChangeListener> timeChangeListeners;

    public TimeController(TKeyBoard keyBoard) {
        timeChangeListeners = new ArrayList<>();
        keyBoard.addTappedListener(this);
    }
    public void registerOnTimeChange(OnTimeChangeListener listener) {
        timeChangeListeners.add(listener);
    }

    @Override
    public void onTapped(TKey button) {
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
        for (OnTimeChangeListener listener : timeChangeListeners) {
            listener.updateTimeMultipler(multipler);
        }
    }
}
