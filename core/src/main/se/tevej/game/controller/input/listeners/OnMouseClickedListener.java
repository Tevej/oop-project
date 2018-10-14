package main.se.tevej.game.controller.input.listeners;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TKey;

public interface OnMouseClickedListener {

    void onClicked(TMouse mouse, TKey key);

}
