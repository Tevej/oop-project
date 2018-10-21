package main.se.tevej.game.model.entities;

public class NoSuchBuildingException extends Exception {
    public NoSuchBuildingException(String buildingName) {
        super("No such building " + buildingName);
    }
}
