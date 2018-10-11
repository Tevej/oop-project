package main.se.tevej.game.exceptions;

public class MismatchedResourceException extends Exception {
    public MismatchedResourceException() {
        super("The resources are not the same");
    }
}
