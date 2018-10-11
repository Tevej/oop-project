package main.se.tevej.game.model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.se.tevej.game.model.components.buildings.BuildingType;

public class Cost {
    private static HashMap<BuildingType, List<Resource>> buildingCost =
        new HashMap<BuildingType, List<Resource>>() {
            {
                put(BuildingType.HOME, new ArrayList<>(Arrays.asList(
                    new Resource(100, ResourceType.WOOD),
                    new Resource(100, ResourceType.WATER))));
                put(BuildingType.LUMBERMILL, new ArrayList<>(Arrays.asList(
                    new Resource(300, ResourceType.WOOD),
                    new Resource(200, ResourceType.STONE))));
                put(BuildingType.QUARRY, new ArrayList<>(Arrays.asList(
                    new Resource(100, ResourceType.WOOD),
                    new Resource(100, ResourceType.WATER),
                    new Resource(200, ResourceType.STONE))));
                put(BuildingType.PUMP, new ArrayList<>(Arrays.asList(
                    new Resource(100, ResourceType.WOOD),
                    new Resource(400, ResourceType.STONE))));

            }
        };

    public static List<Resource> getCostOfBuilding(BuildingType type) {
        return buildingCost.get(type);
    }
}