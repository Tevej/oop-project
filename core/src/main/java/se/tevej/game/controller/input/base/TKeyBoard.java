package main.java.se.tevej.game.controller.input.base;

/**
 * A method specification for our KeyboardAdapters. It also makes the change of framework easier.
 */
public interface TKeyBoard {

    void addTappedListener(OnTappedListener onTappedListener);

}
