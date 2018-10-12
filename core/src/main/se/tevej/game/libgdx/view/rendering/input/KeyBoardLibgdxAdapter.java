package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.*;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTappedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyBoardLibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    static final Map<Integer, TKey> inputMap = new HashMap<>();

    static
    {
        inputMap.put(NUM_1, TKey.KEY_1);
        inputMap.put(NUM_2, TKey.KEY_2);
        inputMap.put(NUM_3, TKey.KEY_3);
        inputMap.put(NUM_4, TKey.KEY_4);
        inputMap.put(NUM_5, TKey.KEY_5);
        inputMap.put(NUM_6, TKey.KEY_6);
        inputMap.put(NUM_7, TKey.KEY_7);
        inputMap.put(NUM_8, TKey.KEY_8);
        inputMap.put(NUM_9, TKey.KEY_9);
        inputMap.put(NUM_0, TKey.KEY_0);

        inputMap.put(A, TKey.KEY_A);
        inputMap.put(B, TKey.KEY_B);
        inputMap.put(C, TKey.KEY_C);
        inputMap.put(D, TKey.KEY_D);
        inputMap.put(E, TKey.KEY_E);
        inputMap.put(F, TKey.KEY_F);
        inputMap.put(G, TKey.KEY_G);
        inputMap.put(H, TKey.KEY_H);
        inputMap.put(I, TKey.KEY_I);
        inputMap.put(J, TKey.KEY_J);
        inputMap.put(K, TKey.KEY_K);
        inputMap.put(L, TKey.KEY_L);
        inputMap.put(M, TKey.KEY_M);
        inputMap.put(N, TKey.KEY_N);
        inputMap.put(O, TKey.KEY_O);
        inputMap.put(P, TKey.KEY_P);
        inputMap.put(Q, TKey.KEY_Q);
        inputMap.put(R, TKey.KEY_R);
        inputMap.put(S, TKey.KEY_S);
        inputMap.put(T, TKey.KEY_T);
        inputMap.put(U, TKey.KEY_U);
        inputMap.put(V, TKey.KEY_V);
        inputMap.put(W, TKey.KEY_W);
        inputMap.put(X, TKey.KEY_X);
        inputMap.put(Y, TKey.KEY_Y);
        inputMap.put(Z, TKey.KEY_Z);

        inputMap.put(LEFT, TKey.KEY_LEFT);
        inputMap.put(RIGHT, TKey.KEY_RIGHT);
        inputMap.put(UP, TKey.KEY_UP);
        inputMap.put(DOWN, TKey.KEY_DOWN);

        inputMap.put(ENTER, TKey.KEY_ENTER);
        inputMap.put(ESCAPE, TKey.KEY_ESC);
        inputMap.put(CONTROL_LEFT, TKey.KEY_CTRL);
        inputMap.put(SHIFT_LEFT, TKey.KEY_SHIFT);
        inputMap.put(TAB, TKey.KEY_TAB);
        inputMap.put(ALT_LEFT, TKey.KEY_ALT);
        inputMap.put(SPACE, TKey.KEY_SPACE);

    }

    @Override
    public void addTappedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
               if (inputMap.containsKey(keycode)) {
                   onClickedListener.onTapped(inputMap.get(keycode));
               }
               return false;
            }
        });
    }

}
