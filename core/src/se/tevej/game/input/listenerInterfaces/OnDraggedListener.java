package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TMouse;
import se.tevej.game.input.enums.TButton;

public interface OnDraggedListener {
    void onDragged(TMouse mouse, TButton button, float x, float y );
}
