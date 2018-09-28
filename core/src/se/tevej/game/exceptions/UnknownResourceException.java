package se.tevej.game.exceptions;

import se.tevej.game.model.resource.ResourceType;

public class UnknownResourceException extends Exception {
    public UnknownResourceException(ResourceType resourceType) { super("Unknown resource " + resourceType.toString()); }
}
