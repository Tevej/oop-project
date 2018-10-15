package main.se.tevej.game.model.components.buildings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public final class BuildingCostUtils {

    private static final Map<BuildingType, List<Resource>> BUILDING_COST = new HashMap<>();

    static {
        BUILDING_COST.put(BuildingType.HOME, new ArrayList<>(Arrays.asList(
            new Resource(100, ResourceType.WOOD),
            new Resource(100, ResourceType.WATER))));
        BUILDING_COST.put(BuildingType.LUMBERMILL, new ArrayList<>(Arrays.asList(
            new Resource(300, ResourceType.WOOD),
            new Resource(200, ResourceType.STONE))));
        BUILDING_COST.put(BuildingType.QUARRY, new ArrayList<>(Arrays.asList(
            new Resource(100, ResourceType.WOOD),
            new Resource(100, ResourceType.WATER),
            new Resource(200, ResourceType.STONE))));
        BUILDING_COST.put(BuildingType.PUMP, new ArrayList<>(Arrays.asList(
            new Resource(100, ResourceType.WOOD),
            new Resource(400, ResourceType.STONE))));
    }

    private BuildingCostUtils() {

    }

    public static List<Resource> getCostOfBuilding(BuildingType type) {
        return BUILDING_COST.get(type);
    }
}