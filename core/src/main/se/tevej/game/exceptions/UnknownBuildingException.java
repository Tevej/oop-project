package main.se.tevej.game.exceptions;

public class UnknownBuildingException extends Exception {
    public UnknownBuildingException() {
        super("Unknown building");
    }
}
