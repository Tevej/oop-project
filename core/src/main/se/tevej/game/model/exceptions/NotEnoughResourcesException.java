package main.se.tevej.game.model.exceptions;

public class NotEnoughResourcesException extends Exception {
    public NotEnoughResourcesException() {
        super("Trying to extract more resources than is available.");
    }
}
