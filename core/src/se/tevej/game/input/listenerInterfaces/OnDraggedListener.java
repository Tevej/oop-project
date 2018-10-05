package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TMouse;
import se.tevej.game.input.inputEnums.MouseButton;

public interface OnDraggedListener {
    void onDragged(TMouse mouse, MouseButton button, float x, float y );
}
