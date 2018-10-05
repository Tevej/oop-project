package main.se.tevej.game.exceptions;

public class MissmatchedResourceException extends Exception {
    public MissmatchedResourceException() {
        super("The resources are not the same");
    }
}
