package main.se.tevej.game.model.entities;

/**
 * This exception is thrown if we try to build/delete/pay_for a buildingtype that does
 * not yet exist in our model.
 */
public class NoSuchBuildingException extends Exception {
    public NoSuchBuildingException(String buildingName) {
        super("No such building " + buildingName);
    }
}
