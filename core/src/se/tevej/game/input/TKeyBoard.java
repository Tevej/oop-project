package se.tevej.game.input;

import se.tevej.game.input.listenerInterfaces.OnTappedListener;

import static com.badlogic.gdx.Input.Keys.*;

public interface TKeyBoard {

    TKeyBoard addClickedListener(OnTappedListener onClickedListener);

}
