package main.se.tevej.game.controller.input.listeners;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TButton;

public interface OnDraggedListener {
    void onDragged(TMouse mouse, TButton button, float x, float y);
}
