package main.se.tevej.game.controller.input.libgdx;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;
import main.se.tevej.game.libgdx.view.rendering.input.OrderedInputMultiplexer;

public class KeyBoardLibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    private static final Map<Integer, TKey> INPUT_MAP = new HashMap<>();

    static {
        INPUT_MAP.put(Keys.NUM_1, TKey.KEY_1);
        INPUT_MAP.put(Keys.NUM_2, TKey.KEY_2);
        INPUT_MAP.put(Keys.NUM_3, TKey.KEY_3);
        INPUT_MAP.put(Keys.NUM_4, TKey.KEY_4);
        INPUT_MAP.put(Keys.NUM_5, TKey.KEY_5);
        INPUT_MAP.put(Keys.NUM_6, TKey.KEY_6);
        INPUT_MAP.put(Keys.NUM_7, TKey.KEY_7);
        INPUT_MAP.put(Keys.NUM_8, TKey.KEY_8);
        INPUT_MAP.put(Keys.NUM_9, TKey.KEY_9);
        INPUT_MAP.put(Keys.NUM_0, TKey.KEY_0);

        INPUT_MAP.put(Keys.A, TKey.KEY_A);
        INPUT_MAP.put(Keys.B, TKey.KEY_B);
        INPUT_MAP.put(Keys.C, TKey.KEY_C);
        INPUT_MAP.put(Keys.D, TKey.KEY_D);
        INPUT_MAP.put(Keys.E, TKey.KEY_E);
        INPUT_MAP.put(Keys.F, TKey.KEY_F);
        INPUT_MAP.put(Keys.G, TKey.KEY_G);
        INPUT_MAP.put(Keys.H, TKey.KEY_H);
        INPUT_MAP.put(Keys.I, TKey.KEY_I);
        INPUT_MAP.put(Keys.J, TKey.KEY_J);
        INPUT_MAP.put(Keys.K, TKey.KEY_K);
        INPUT_MAP.put(Keys.L, TKey.KEY_L);
        INPUT_MAP.put(Keys.M, TKey.KEY_M);
        INPUT_MAP.put(Keys.N, TKey.KEY_N);
        INPUT_MAP.put(Keys.O, TKey.KEY_O);
        INPUT_MAP.put(Keys.P, TKey.KEY_P);
        INPUT_MAP.put(Keys.Q, TKey.KEY_Q);
        INPUT_MAP.put(Keys.R, TKey.KEY_R);
        INPUT_MAP.put(Keys.S, TKey.KEY_S);
        INPUT_MAP.put(Keys.T, TKey.KEY_T);
        INPUT_MAP.put(Keys.U, TKey.KEY_U);
        INPUT_MAP.put(Keys.V, TKey.KEY_V);
        INPUT_MAP.put(Keys.W, TKey.KEY_W);
        INPUT_MAP.put(Keys.X, TKey.KEY_X);
        INPUT_MAP.put(Keys.Y, TKey.KEY_Y);
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

    public KeyBoardLibgdxAdapter() {
        super();
    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        OrderedInputMultiplexer.getInstance().add(TKeyBoard.class, new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (INPUT_MAP.containsKey(keycode)) {
                    onClickedListener.onTapped(keyboard, INPUT_MAP.get(keycode));
                }
                return true;
            }
        });
    }

}
