package main.se.tevej.game.controller.time;

import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.controller.input.base.OnTappedListener;
import main.se.tevej.game.controller.input.base.TKey;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.view.gui.time.OnTimeChangeListener;
import main.se.tevej.game.view.gui.time.RegisterTimeController;
import main.se.tevej.game.view.gui.time.SetTimeMultiplier;

/**
 * The controller responsible for changing time as user input dictates.
 */
public class TimeController implements OnTappedListener, RegisterTimeController, SetTimeMultiplier {
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
                setMultiplierTo(0);
                break;
            case KEY_1:
                setMultiplierTo(1);
                break;
            case KEY_2:
                setMultiplierTo(2);
                break;
            case KEY_3:
                setMultiplierTo(5);
                break;
            case KEY_4:
                setMultiplierTo(20);
                break;
            default:
                break;
        }
    }

    private void setMultiplierTo(float multiplier) {
        for (OnTimeChangeListener listener : onChangeListeners) {
            listener.updateTimeMultiplier(multiplier);
        }
    }

    @Override
    public void registerTimeController(OnTimeChangeListener onTimeChange) {
        registerOnTimeChange(onTimeChange);
    }

    @Override
    public void setTimeMultiplier(float newScale) {
        setMultiplierTo(newScale);
    }
}
