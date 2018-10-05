package main.se.tevej.game.input.listenerInterfaces;

import main.se.tevej.game.input.TMouse;
import main.se.tevej.game.input.enums.TButton;

public interface OnDraggedListener {
    void onDragged(TMouse mouse, TButton button, float x, float y );
}
