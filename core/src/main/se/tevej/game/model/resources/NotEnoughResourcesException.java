package main.se.tevej.game.model.resources;

public class NotEnoughResourcesException extends Exception {
    public NotEnoughResourcesException() {
        super("Trying to extract more resources than is available.");
    }
}
