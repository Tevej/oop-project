package main.se.tevej.game.input;

import main.se.tevej.game.input.listenerInterfaces.OnTappedListener;


public interface TKeyBoard {

    TKeyBoard addClickedListener(OnTappedListener onClickedListener);

}
