package main.java.se.tevej.game.controller.input.base;

/**
 * A specification for our classes which listens for when the user clicks with the mouse.
 */
public interface OnMouseClickedListener {

    void onClicked(TMouse mouse, TKey key);

}
