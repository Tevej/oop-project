package main.se.tevej.game.controller.input.listeners;

import main.se.tevej.game.controller.input.enums.TKey;

public interface OnDraggedListener {
    void onDragged(TKey button, float x, float y );
}
