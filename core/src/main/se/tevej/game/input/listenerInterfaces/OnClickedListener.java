package main.se.tevej.game.input.listenerInterfaces;

import main.se.tevej.game.input.enums.TButton;
import main.se.tevej.game.input.TMouse;

public interface OnClickedListener {
    void onClicked(TMouse mouse, TButton button);
}
