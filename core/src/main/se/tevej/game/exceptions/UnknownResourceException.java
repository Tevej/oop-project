package main.se.tevej.game.exceptions;

import main.se.tevej.game.model.utils.ResourceType;

public class UnknownResourceException extends Exception {
    public UnknownResourceException() {
        super("Unknown resource");
    }
}
