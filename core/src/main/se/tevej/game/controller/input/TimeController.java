package main.se.tevej.game.controller.input;

import java.util.ArrayList;
import java.util.List;

import main.se.tevej.game.controller.input.enums.TButton;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;
import main.se.tevej.game.controller.input.listeners.OnTimeChangeListener;
import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;

public class TimeController implements OnTappedListener {
    private List<OnTimeChangeListener> onChangeListeners;

    public TimeController() {
        onChangeListeners = new ArrayList<>();
        new InputLibgdxFactory().createKeyBoard().addTappedListener(this);
    }

    public void registerOnTimeChange(OnTimeChangeListener listener) {
        onChangeListeners.add(listener);
    }

    @Override
    public void onTapped(TKeyBoard keyBoard, TButton button) {
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
        for (OnTimeChangeListener listener : timeChangeListeners) {
            listener.updateTimeMultipler(multiplier);
        }
    }
}
