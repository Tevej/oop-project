package main.se.tevej.game.controller.input.listenerInterfaces;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TButton;

public interface OnTappedListener {
    void onTapped(TKeyBoard keyBoard, TButton button);
}
