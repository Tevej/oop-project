package main.se.tevej.game.model.exceptions;

import main.se.tevej.game.model.utils.ResourceType;

public class UnknownResourceException extends Exception {
    public UnknownResourceException(ResourceType resourceType) {
        super("Unknown utils " + resourceType.toString());
    }
}
