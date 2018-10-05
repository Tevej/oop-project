package se.tevej.game.input;

import se.tevej.game.input.listenerInterfaces.OnTappedListener;


public interface TKeyBoard {

    TKeyBoard addClickedListener(OnTappedListener onClickedListener);

}
