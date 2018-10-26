package main.java.se.tevej.game.controller.input.base;

/**
 * Interface for our Inputfactory so that we can seperate ourselves from our LibGDX implementations.
 */
public interface InputFactory {
    TMouse createMouse();

    TKeyBoard createKeyBoard();
}
