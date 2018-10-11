package main.se.tevej.game.libgdx.view.rendering.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TButton;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTappedListener;

import static com.badlogic.gdx.Input.Keys.A;
import static com.badlogic.gdx.Input.Keys.ALT_LEFT;
import static com.badlogic.gdx.Input.Keys.B;
import static com.badlogic.gdx.Input.Keys.C;
import static com.badlogic.gdx.Input.Keys.CONTROL_LEFT;
import static com.badlogic.gdx.Input.Keys.D;
import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.badlogic.gdx.Input.Keys.E;
import static com.badlogic.gdx.Input.Keys.ENTER;
import static com.badlogic.gdx.Input.Keys.ESCAPE;
import static com.badlogic.gdx.Input.Keys.F;
import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.Input.Keys.H;
import static com.badlogic.gdx.Input.Keys.I;
import static com.badlogic.gdx.Input.Keys.J;
import static com.badlogic.gdx.Input.Keys.K;
import static com.badlogic.gdx.Input.Keys.L;
import static com.badlogic.gdx.Input.Keys.LEFT;
import static com.badlogic.gdx.Input.Keys.M;
import static com.badlogic.gdx.Input.Keys.N;
import static com.badlogic.gdx.Input.Keys.NUM_0;
import static com.badlogic.gdx.Input.Keys.NUM_1;
import static com.badlogic.gdx.Input.Keys.NUM_2;
import static com.badlogic.gdx.Input.Keys.NUM_3;
import static com.badlogic.gdx.Input.Keys.NUM_4;
import static com.badlogic.gdx.Input.Keys.NUM_5;
import static com.badlogic.gdx.Input.Keys.NUM_6;
import static com.badlogic.gdx.Input.Keys.NUM_7;
import static com.badlogic.gdx.Input.Keys.NUM_8;
import static com.badlogic.gdx.Input.Keys.NUM_9;
import static com.badlogic.gdx.Input.Keys.O;
import static com.badlogic.gdx.Input.Keys.P;
import static com.badlogic.gdx.Input.Keys.Q;
import static com.badlogic.gdx.Input.Keys.R;
import static com.badlogic.gdx.Input.Keys.RIGHT;
import static com.badlogic.gdx.Input.Keys.S;
import static com.badlogic.gdx.Input.Keys.SHIFT_LEFT;
import static com.badlogic.gdx.Input.Keys.SPACE;
import static com.badlogic.gdx.Input.Keys.T;
import static com.badlogic.gdx.Input.Keys.TAB;
import static com.badlogic.gdx.Input.Keys.U;
import static com.badlogic.gdx.Input.Keys.UP;
import static com.badlogic.gdx.Input.Keys.V;
import static com.badlogic.gdx.Input.Keys.W;
import static com.badlogic.gdx.Input.Keys.X;
import static com.badlogic.gdx.Input.Keys.Y;
import static com.badlogic.gdx.Input.Keys.Z;

public class KeyBoardLibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    static final Map<Integer, TButton> inputMap = new HashMap<>();

    static {
        inputMap.put(NUM_1, TButton.KEY_1);
        inputMap.put(NUM_2, TButton.KEY_2);
        inputMap.put(NUM_3, TButton.KEY_3);
        inputMap.put(NUM_4, TButton.KEY_4);
        inputMap.put(NUM_5, TButton.KEY_5);
        inputMap.put(NUM_6, TButton.KEY_6);
        inputMap.put(NUM_7, TButton.KEY_7);
        inputMap.put(NUM_8, TButton.KEY_8);
        inputMap.put(NUM_9, TButton.KEY_9);
        inputMap.put(NUM_0, TButton.KEY_0);

        inputMap.put(A, TButton.KEY_A);
        inputMap.put(B, TButton.KEY_B);
        inputMap.put(C, TButton.KEY_C);
        inputMap.put(D, TButton.KEY_D);
        inputMap.put(E, TButton.KEY_E);
        inputMap.put(F, TButton.KEY_F);
        inputMap.put(G, TButton.KEY_G);
        inputMap.put(H, TButton.KEY_H);
        inputMap.put(I, TButton.KEY_I);
        inputMap.put(J, TButton.KEY_J);
        inputMap.put(K, TButton.KEY_K);
        inputMap.put(L, TButton.KEY_L);
        inputMap.put(M, TButton.KEY_M);
        inputMap.put(N, TButton.KEY_N);
        inputMap.put(O, TButton.KEY_O);
        inputMap.put(P, TButton.KEY_P);
        inputMap.put(Q, TButton.KEY_Q);
        inputMap.put(R, TButton.KEY_R);
        inputMap.put(S, TButton.KEY_S);
        inputMap.put(T, TButton.KEY_T);
        inputMap.put(U, TButton.KEY_U);
        inputMap.put(V, TButton.KEY_V);
        inputMap.put(W, TButton.KEY_W);
        inputMap.put(X, TButton.KEY_X);
        inputMap.put(Y, TButton.KEY_Y);
        inputMap.put(Z, TButton.KEY_Z);

        inputMap.put(LEFT, TButton.KEY_LEFT);
        inputMap.put(RIGHT, TButton.KEY_RIGHT);
        inputMap.put(UP, TButton.KEY_UP);
        inputMap.put(DOWN, TButton.KEY_DOWN);

        inputMap.put(ENTER, TButton.KEY_ENTER);
        inputMap.put(ESCAPE, TButton.KEY_ESC);
        inputMap.put(CONTROL_LEFT, TButton.KEY_CTRL);
        inputMap.put(SHIFT_LEFT, TButton.KEY_SHIFT);
        inputMap.put(TAB, TButton.KEY_TAB);
        inputMap.put(ALT_LEFT, TButton.KEY_ALT);
        inputMap.put(SPACE, TButton.KEY_SPACE);

    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (inputMap.containsKey(keycode)) {
                    onClickedListener.onTapped(keyboard, inputMap.get(keycode));
                }
                return true;
            }
        });
    }

}
