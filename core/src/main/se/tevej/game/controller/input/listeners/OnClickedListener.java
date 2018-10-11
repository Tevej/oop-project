package main.se.tevej.game.controller.input.listeners;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TButton;

public interface OnClickedListener {
    void onClicked(TMouse mouse, TButton button);
}
