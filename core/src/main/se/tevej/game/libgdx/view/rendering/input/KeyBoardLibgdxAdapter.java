package main.se.tevej.game.libgdx.view.rendering.input;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.libgdx.InputLibgdxAdapter;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.InputAdapter;

public class KeyBoardLibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    static final Map<Integer, TKey> inputMap = new HashMap<>();

    static {
        inputMap.put(Keys.NUM_1, TKey.KEY_1);
        inputMap.put(Keys.NUM_2, TKey.KEY_2);
        inputMap.put(Keys.NUM_3, TKey.KEY_3);
        inputMap.put(Keys.NUM_4, TKey.KEY_4);
        inputMap.put(Keys.NUM_5, TKey.KEY_5);
        inputMap.put(Keys.NUM_6, TKey.KEY_6);
        inputMap.put(Keys.NUM_7, TKey.KEY_7);
        inputMap.put(Keys.NUM_8, TKey.KEY_8);
        inputMap.put(Keys.NUM_9, TKey.KEY_9);
        inputMap.put(Keys.NUM_0, TKey.KEY_0);

        inputMap.put(Keys.A, TKey.KEY_A);
        inputMap.put(Keys.B, TKey.KEY_B);
        inputMap.put(Keys.C, TKey.KEY_C);
        inputMap.put(Keys.D, TKey.KEY_D);
        inputMap.put(Keys.E, TKey.KEY_E);
        inputMap.put(Keys.F, TKey.KEY_F);
        inputMap.put(Keys.G, TKey.KEY_G);
        inputMap.put(Keys.H, TKey.KEY_H);
        inputMap.put(Keys.I, TKey.KEY_I);
        inputMap.put(Keys.J, TKey.KEY_J);
        inputMap.put(Keys.K, TKey.KEY_K);
        inputMap.put(Keys.L, TKey.KEY_L);
        inputMap.put(Keys.M, TKey.KEY_M);
        inputMap.put(Keys.N, TKey.KEY_N);
        inputMap.put(Keys.O, TKey.KEY_O);
        inputMap.put(Keys.P, TKey.KEY_P);
        inputMap.put(Keys.Q, TKey.KEY_Q);
        inputMap.put(Keys.R, TKey.KEY_R);
        inputMap.put(Keys.S, TKey.KEY_S);
        inputMap.put(Keys.T, TKey.KEY_T);
        inputMap.put(Keys.U, TKey.KEY_U);
        inputMap.put(Keys.V, TKey.KEY_V);
        inputMap.put(Keys.W, TKey.KEY_W);
        inputMap.put(Keys.X, TKey.KEY_X);
        inputMap.put(Keys.Y, TKey.KEY_Y);
        inputMap.put(Keys.Z, TKey.KEY_Z);

        inputMap.put(Keys.LEFT, TKey.KEY_LEFT);
        inputMap.put(Keys.RIGHT, TKey.KEY_RIGHT);
        inputMap.put(Keys.UP, TKey.KEY_UP);
        inputMap.put(Keys.DOWN, TKey.KEY_DOWN);

        inputMap.put(Keys.ENTER, TKey.KEY_ENTER);
        inputMap.put(Keys.ESCAPE, TKey.KEY_ESC);
        inputMap.put(Keys.CONTROL_LEFT, TKey.KEY_CTRL);
        inputMap.put(Keys.SHIFT_LEFT, TKey.KEY_SHIFT);
        inputMap.put(Keys.TAB, TKey.KEY_TAB);
        inputMap.put(Keys.ALT_LEFT, TKey.KEY_ALT);
        inputMap.put(Keys.SPACE, TKey.KEY_SPACE);

    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        main.se.tevej.game.libgdx.view.rendering.input.OrderedInputMultiplexer.getInstance().add(TKeyBoard.class, new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (inputMap.containsKey(keycode)) {
                    onClickedListener.onTapped(keyboard, inputMap.get(keycode));
                }
                return false;
            }
        });
    }

}
