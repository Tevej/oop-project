package main.se.tevej.game.input.listenerInterfaces;

import main.se.tevej.game.input.TKeyBoard;
import main.se.tevej.game.input.enums.TButton;

public interface OnTappedListener {
    void onTapped (TKeyBoard keyBoard, TButton button);
}
