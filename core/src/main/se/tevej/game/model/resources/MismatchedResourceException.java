package main.se.tevej.game.model.resources;

/**
 * A custom, more descriptive, exception that tells us we tried to use the wrong resource.
 * For example, extract the wrong resource with a gatherer.
 */
public class MismatchedResourceException extends Exception {
    public MismatchedResourceException() {
        super("The resources are not the same");
    }
}
