package se.tevej.game.input.listenerInterfaces;

import se.tevej.game.input.TMouse;
import se.tevej.game.input.enums.TKey;

public interface OnDraggedListener {
    void onDragged(TMouse mouse, TKey button, float x, float y );
}
