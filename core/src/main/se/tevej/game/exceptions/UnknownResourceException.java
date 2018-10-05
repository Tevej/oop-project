package main.se.tevej.game.exceptions;


import main.se.tevej.game.model.resource.ResourceType;

public class UnknownResourceException extends Exception {
    public UnknownResourceException(ResourceType resourceType) { super("Unknown resource " + resourceType.toString()); }
}
