package main.se.tevej.game.controller.input.libgdx;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TButton;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;

public class KeyBoardLibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    private static final Map<Integer, TButton> INPUT_MAP = new HashMap<>();

    static {
        INPUT_MAP.put(Keys.NUM_1, TButton.KEY_1);
        INPUT_MAP.put(Keys.NUM_2, TButton.KEY_2);
        INPUT_MAP.put(Keys.NUM_3, TButton.KEY_3);
        INPUT_MAP.put(Keys.NUM_4, TButton.KEY_4);
        INPUT_MAP.put(Keys.NUM_5, TButton.KEY_5);
        INPUT_MAP.put(Keys.NUM_6, TButton.KEY_6);
        INPUT_MAP.put(Keys.NUM_7, TButton.KEY_7);
        INPUT_MAP.put(Keys.NUM_8, TButton.KEY_8);
        INPUT_MAP.put(Keys.NUM_9, TButton.KEY_9);
        INPUT_MAP.put(Keys.NUM_0, TButton.KEY_0);

        INPUT_MAP.put(Keys.A, TButton.KEY_A);
        INPUT_MAP.put(Keys.B, TButton.KEY_B);
        INPUT_MAP.put(Keys.C, TButton.KEY_C);
        INPUT_MAP.put(Keys.D, TButton.KEY_D);
        INPUT_MAP.put(Keys.E, TButton.KEY_E);
        INPUT_MAP.put(Keys.F, TButton.KEY_F);
        INPUT_MAP.put(Keys.G, TButton.KEY_G);
        INPUT_MAP.put(Keys.H, TButton.KEY_H);
        INPUT_MAP.put(Keys.I, TButton.KEY_I);
        INPUT_MAP.put(Keys.J, TButton.KEY_J);
        INPUT_MAP.put(Keys.K, TButton.KEY_K);
        INPUT_MAP.put(Keys.L, TButton.KEY_L);
        INPUT_MAP.put(Keys.M, TButton.KEY_M);
        INPUT_MAP.put(Keys.N, TButton.KEY_N);
        INPUT_MAP.put(Keys.O, TButton.KEY_O);
        INPUT_MAP.put(Keys.P, TButton.KEY_P);
        INPUT_MAP.put(Keys.Q, TButton.KEY_Q);
        INPUT_MAP.put(Keys.R, TButton.KEY_R);
        INPUT_MAP.put(Keys.S, TButton.KEY_S);
        INPUT_MAP.put(Keys.T, TButton.KEY_T);
        INPUT_MAP.put(Keys.U, TButton.KEY_U);
        INPUT_MAP.put(Keys.V, TButton.KEY_V);
        INPUT_MAP.put(Keys.W, TButton.KEY_W);
        INPUT_MAP.put(Keys.X, TButton.KEY_X);
        INPUT_MAP.put(Keys.Y, TButton.KEY_Y);
        INPUT_MAP.put(Keys.Z, TButton.KEY_Z);

        INPUT_MAP.put(Keys.LEFT, TButton.KEY_LEFT);
        INPUT_MAP.put(Keys.RIGHT, TButton.KEY_RIGHT);
        INPUT_MAP.put(Keys.UP, TButton.KEY_UP);
        INPUT_MAP.put(Keys.DOWN, TButton.KEY_DOWN);

        INPUT_MAP.put(Keys.ENTER, TButton.KEY_ENTER);
        INPUT_MAP.put(Keys.ESCAPE, TButton.KEY_ESC);
        INPUT_MAP.put(Keys.CONTROL_LEFT, TButton.KEY_CTRL);
        INPUT_MAP.put(Keys.SHIFT_LEFT, TButton.KEY_SHIFT);
        INPUT_MAP.put(Keys.TAB, TButton.KEY_TAB);
        INPUT_MAP.put(Keys.ALT_LEFT, TButton.KEY_ALT);
        INPUT_MAP.put(Keys.SPACE, TButton.KEY_SPACE);

    }

    public KeyBoardLibgdxAdapter() {
        super();
    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
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
