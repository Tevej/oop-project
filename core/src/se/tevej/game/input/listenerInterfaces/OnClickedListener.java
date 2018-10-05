package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TMouse;
import se.tevej.game.input.inputEnums.MouseButton;

public interface OnClickedListener {
    void onClicked(TMouse mouse, MouseButton button);
}
