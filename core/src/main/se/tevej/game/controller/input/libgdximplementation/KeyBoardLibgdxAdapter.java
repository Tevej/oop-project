package main.se.tevej.game.controller.input.libgdximplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.base.OnTappedListener;
import main.se.tevej.game.controller.input.base.TKey;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.view.gui.base.InputProcessorListener;

public class KeyBoardLibgdxAdapter extends InputAdapter implements TKeyBoard {

    private static final Map<Integer, TKey> INPUT_MAP;

    static {
        INPUT_MAP = new HashMap<>();
        INPUT_MAP.put(Keys.NUM_1, TKey.KEY_1);
        INPUT_MAP.put(Keys.NUM_2, TKey.KEY_2);
        INPUT_MAP.put(Keys.NUM_3, TKey.KEY_3);
        INPUT_MAP.put(Keys.NUM_4, TKey.KEY_4);
        INPUT_MAP.put(Keys.L, TKey.KEY_L);
        INPUT_MAP.put(Keys.P, TKey.KEY_P);
        INPUT_MAP.put(Keys.Q, TKey.KEY_Q);
        INPUT_MAP.put(Keys.X, TKey.KEY_X);
        INPUT_MAP.put(Keys.Z, TKey.KEY_Z);

        INPUT_MAP.put(Keys.LEFT, TKey.KEY_LEFT);
        INPUT_MAP.put(Keys.RIGHT, TKey.KEY_RIGHT);
        INPUT_MAP.put(Keys.UP, TKey.KEY_UP);
        INPUT_MAP.put(Keys.DOWN, TKey.KEY_DOWN);

        INPUT_MAP.put(Keys.ENTER, TKey.KEY_ENTER);
        INPUT_MAP.put(Keys.ESCAPE, TKey.KEY_ESC);
        INPUT_MAP.put(Keys.CONTROL_LEFT, TKey.KEY_CTRL);
        INPUT_MAP.put(Keys.SHIFT_LEFT, TKey.KEY_SHIFT);
        INPUT_MAP.put(Keys.TAB, TKey.KEY_TAB);
        INPUT_MAP.put(Keys.ALT_LEFT, TKey.KEY_ALT);
        INPUT_MAP.put(Keys.SPACE, TKey.KEY_SPACE);

    }

    private List<OnTappedListener> onTappedListeners;

    public KeyBoardLibgdxAdapter(InputProcessorListener listener) {
        super();
        onTappedListeners = new ArrayList<>();
        listener.addGameRenderingInputProcessor(this);
    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        onTappedListeners.add(onClickedListener);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (INPUT_MAP.containsKey(keycode)) {
            for (OnTappedListener listener : onTappedListeners) {
                listener.onTapped(this, INPUT_MAP.get(keycode));
            }
        }
        return true;
    }
}
