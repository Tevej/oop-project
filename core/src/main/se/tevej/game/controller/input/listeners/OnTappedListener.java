package main.se.tevej.game.controller.input.listeners;

import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.enums.TKey;

public interface OnTappedListener {
    void onTapped(TKeyBoard keyBoard, TKey button);
}
