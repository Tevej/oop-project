package main.se.tevej.game.exceptions;

import main.se.tevej.game.model.components.buildings.BuildingType;

public class NoSuchBuildingException extends Exception {
    public NoSuchBuildingException(BuildingType type) {
        super("No such building " + type);
    }
}
