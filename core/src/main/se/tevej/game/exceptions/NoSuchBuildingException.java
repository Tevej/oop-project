package main.se.tevej.game.exceptions;

public class NoSuchBuildingException extends Exception {
    public NoSuchBuildingException() {
        super("No such building found");
    }
}
