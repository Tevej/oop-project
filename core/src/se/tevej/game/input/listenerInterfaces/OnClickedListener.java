package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TMouse;
import se.tevej.game.input.enums.TButton;

public interface OnClickedListener {
    void onClicked(TMouse mouse, TButton button);
}
