package main.se.tevej.game.exceptions;

import main.se.tevej.game.model.components.buildings.BuildingType;

public class UnknownBuildingException extends Exception {
    public UnknownBuildingException() {
        super("Unknown building");
    }
}
