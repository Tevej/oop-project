package se.tevej.game.input;

import static com.badlogic.gdx.Input.Keys.*;

public interface TKeyBoard {

    TKeyBoard addClickedListener(OnClickedListener onClickedListener);

    interface OnClickedListener {
        void onClicked (TKeyBoard keyBoard, Key button);
    }

    enum Key {
        KEY_1(NUM_1),
        KEY_2(NUM_2),
        KEY_3(NUM_3),
        KEY_4(NUM_4),
        KEY_5(NUM_5),
        KEY_6(NUM_6),
        KEY_7(NUM_7),
        KEY_8(NUM_8),
        KEY_9(NUM_9),
        KEY_0(NUM_0),
        KEY_A(A),
        KEY_B(B),
        KEY_C(C),
        KEY_D(D),
        KEY_E(E),
        KEY_F(F),
        KEY_G(G),
        KEY_H(H),
        KEY_I(I),
        KEY_J(J),
        KEY_K(K),
        KEY_L(L),
        KEY_M(M),
        KEY_N(N),
        KEY_O(O),
        KEY_P(P),
        KEY_Q(Q),
        KEY_R(R),
        KEY_S(S),
        KEY_T(T),
        KEY_U(U),
        KEY_V(V),
        KEY_W(W),
        KEY_X(X),
        KEY_Y(Y),
        KEY_Z(Z),
        KEY_LEFT(LEFT),
        KEY_RIGHT(RIGHT),
        KEY_UP(UP),
        KEY_DOWN(DOWN),
        KEY_ENTER(ENTER),
        KEY_ESC(ESCAPE);

        Key(int libgdxKeycode){
            this.LIBGDX_KEY_CODE = libgdxKeycode;
        }

        final int LIBGDX_KEY_CODE;

        public int getLibgdxKeyCode(){
            return LIBGDX_KEY_CODE;
        }
    }
}
