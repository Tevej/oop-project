package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TKeyBoard;
import se.tevej.game.input.enums.TButton;

public interface OnTappedListener {
    void onTapped (TKeyBoard keyBoard, TButton button);
}
