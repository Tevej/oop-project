package main.se.tevej.game.model.components;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

import java.util.*;

public class CostComponent implements Component {
    private final static HashMap<BuildingType, List<Resource>> buildingCost =
            new HashMap<BuildingType, List<Resource>>() {
                {
                    put(BuildingType.HOME, new ArrayList<>(Arrays.asList(
                            new Resource(100,ResourceType.WOOD),
                            new Resource(100,ResourceType.WATER))));
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