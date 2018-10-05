package main.se.tevej.game.input;

import static com.badlogic.gdx.Input.Keys.*;

public interface TKeyBoard {

    TKeyBoard addClickedListener(OnTappedListener onClickedListener);


    interface OnTappedListener {
        /*

        In the implementation use Input.toString(button) to get the name of the key pressed

         */
        void onTapped (TKeyBoard keyBoard, int button);
    }
}
