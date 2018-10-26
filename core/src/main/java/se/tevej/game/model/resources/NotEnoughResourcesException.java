package main.java.se.tevej.game.model.resources;

/**
 * When you try to extract more resource than there currently is, this exception is thrown.
 */
public class NotEnoughResourcesException extends Exception {
    public NotEnoughResourcesException() {
        super("Trying to extract more resources than is available.");
    }
}
