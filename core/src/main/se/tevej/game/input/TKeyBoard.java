package main.se.tevej.game.input;

import static com.badlogic.gdx.Input.Keys.*;

public interface TKeyBoard {

    TKeyBoard addClickedListener(OnClickedListener onClickedListener);


    interface OnClickedListener {
        /*

        In the implementation use Input.toString(button) to get the name of the key pressed

         */
        void onClicked (TKeyBoard keyBoard, int button);
    }
}
