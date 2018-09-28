package main.se.tevej.game.exceptions;

public class NotEnoughResourcesException extends Exception {
    public NotEnoughResourcesException() {
        super("Trying to extract more resources than is available.");
    }
}
