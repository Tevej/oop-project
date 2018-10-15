package main.se.tevej.game.model.exceptions;

public class MismatchedResourceException extends Exception {
    public MismatchedResourceException() {
        super("The resources are not the same");
    }
}
