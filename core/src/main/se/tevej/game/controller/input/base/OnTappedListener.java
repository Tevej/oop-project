package main.se.tevej.game.controller.input.base;

/**
 * A specification for our classes which listens for when the user clicks on a key specified by
 * the Enum TKey.
 */
public interface OnTappedListener {
    void onTapped(TKeyBoard keyBoard, TKey button);
}
