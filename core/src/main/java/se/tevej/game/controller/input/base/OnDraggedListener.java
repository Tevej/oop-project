package main.java.se.tevej.game.controller.input.base;

/**
 * A specification for our classes which listens for when the user drags the mouse.
 * For example, CameraController.
 */
public interface OnDraggedListener {
    void onDragged(TKey button, float x, float y);
}
