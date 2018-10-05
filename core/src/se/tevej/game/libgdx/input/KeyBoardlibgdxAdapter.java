package se.tevej.game.libgdx.input;

import com.badlogic.gdx.*;
import se.tevej.game.input.TKeyBoard;
import se.tevej.game.input.enums.TButton;
import se.tevej.game.input.listenerInterfaces.OnTappedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyBoardlibgdxAdapter extends InputLibgdxAdapter implements TKeyBoard {

    static final Map<Integer, TButton> inputMap = new HashMap<>();

    static
    {
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
    public TKeyBoard addClickedListener(OnTappedListener onClickedListener) {
        TKeyBoard keyboard = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                onClickedListener.onTapped(keyboard, inputMap.get(keycode));
                return true;
            }
        });
        return this;
    }

}
