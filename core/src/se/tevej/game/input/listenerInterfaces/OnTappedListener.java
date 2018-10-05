package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TKeyBoard;

public interface OnTappedListener {
    void onTapped (TKeyBoard keyBoard, TKeyBoard.Key button);
}
