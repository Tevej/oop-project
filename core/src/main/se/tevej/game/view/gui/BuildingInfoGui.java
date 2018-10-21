package main.se.tevej.game.view.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.se.tevej.game.model.components.buildings.BuildingCostUtils;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TUiElement;

public class BuildingInfoGui {
    private static final int ROW_HEIGHT = 40;
    private static final int NUMBER_OF_ROWS = 5;
    private static final int COLUMN_1_WIDTH = 100;
    private static final int COLUMN_2_WIDTH = 40;
    private static final int PADDING = 5;

    private static final Map<BuildingType, String> BUILDING_TO_IMAGE = new HashMap<>();
    private static final Map<BuildingType, String> BUILDING_TO_NAME = new HashMap<>();

    static {
        BUILDING_TO_IMAGE.put(BuildingType.FARM, "buildings/farm.png");
        BUILDING_TO_IMAGE.put(BuildingType.FARM_LAND, "buildings/farm_land.png");
        BUILDING_TO_IMAGE.put(BuildingType.HOME, "buildings/home.png");
        BUILDING_TO_IMAGE.put(BuildingType.LUMBERMILL, "buildings/lumbermill.png");
        BUILDING_TO_IMAGE.put(BuildingType.PUMP, "buildings/pump.png");
        BUILDING_TO_IMAGE.put(BuildingType.QUARRY, "buildings/quarry.png");

        BUILDING_TO_NAME.put(BuildingType.FARM, "Farm");
        BUILDING_TO_NAME.put(BuildingType.FARM_LAND, "Farm land");
        BUILDING_TO_NAME.put(BuildingType.HOME, "Home");
        BUILDING_TO_NAME.put(BuildingType.LUMBERMILL, "Lumber mill");
        BUILDING_TO_NAME.put(BuildingType.PUMP, "Water pump");
        BUILDING_TO_NAME.put(BuildingType.QUARRY, "Quarry");
    }

    private final TTable table;

    private Map<ResourceType, TLabel> buildingToLabelMap;

    private TLabel buildingName;
    private TImage buildingImage;

    public BuildingInfoGui(GuiFactory guiFactory) {
        buildingToLabelMap = new HashMap<>();
        table = guiFactory.createTable();

        initializeTable();
        populateTable(guiFactory);
    }

    public void render() {
        table.render();
    }

    public void updateInfo(BuildingType buildingType) {
        table.visible(buildingType != null);

        if (buildingType != null) {
            updateCostLabels(buildingType);
        }
    }

    private void updateCostLabels(BuildingType buildingType){
        this.buildingName.text(BUILDING_TO_NAME.get(buildingType));
        this.buildingImage.image(BUILDING_TO_IMAGE.get(buildingType));

        resetCostLabels();
        for (Resource resource : BuildingCostUtils.getCostOfBuilding(buildingType)) {
            buildingToLabelMap.get(resource.getType()).text(Double.toString(resource.getAmount()));
        }
    }

    public void update(float deltaTime) {
        table.update(deltaTime);
    }

    private void initializeTable(){
        table
            .positionX((PADDING + COLUMN_1_WIDTH + COLUMN_2_WIDTH + PADDING) / 2f)
            .positionY((NUMBER_OF_ROWS * ROW_HEIGHT) / 2f + PADDING)
            .backgroundColor(0, 0, 0, 0.8f)
            .padding(PADDING)
            .grid(NUMBER_OF_ROWS, 2)
            .visible(false);
    }

    private void populateTable(GuiFactory guiFactory){
        createBuildingNameLabel(guiFactory);
        createBuildingImage(guiFactory);

        createLabelForColumn1(guiFactory, "Water: ");
        createWaterCostLabel(guiFactory);

        createLabelForColumn1(guiFactory, "Stone: ");
        createStoneCostLabel(guiFactory);

        createLabelForColumn1(guiFactory, "Wood: ");
        createWoodCostLabel(guiFactory);

        createLabelForColumn1(guiFactory, "Population: ");
        createPopulationCostLabel(guiFactory);
    }

    private void createBuildingNameLabel(GuiFactory guiFactory){
        buildingName = guiFactory.createLabel().text("Name: ");
        addToTable(buildingName, BuildingInfoGui.COLUMN_1_WIDTH);
    }

    private void createBuildingImage(GuiFactory guiFactory){
        buildingImage = guiFactory.createImage().image("buildings/farm.png");
        addToTable(buildingImage, COLUMN_2_WIDTH);
    }

    private void createWaterCostLabel(GuiFactory guiFactory){
        TLabel waterCost = createModifiableLabelForColumn2(guiFactory);
        buildingToLabelMap.put(ResourceType.WATER, waterCost);
    }

    private void createStoneCostLabel(GuiFactory guiFactory){
        TLabel stoneCost = createModifiableLabelForColumn2(guiFactory);
        buildingToLabelMap.put(ResourceType.STONE, stoneCost);
    }

    private void createWoodCostLabel(GuiFactory guiFactory){
        TLabel woodCost = createModifiableLabelForColumn2(guiFactory);
        buildingToLabelMap.put(ResourceType.WOOD, woodCost);
    }

    private void createPopulationCostLabel(GuiFactory guiFactory){
        TLabel populationCost = createModifiableLabelForColumn2(guiFactory);
        buildingToLabelMap.put(ResourceType.CURRENTPOPULATION, populationCost);
    }

    private void createLabelForColumn1(GuiFactory guiFactory, String text){
        addToTable(guiFactory.createLabel().text(text), COLUMN_1_WIDTH);
    }

    private TLabel createModifiableLabelForColumn2(GuiFactory guiFactory){
        TLabel label = guiFactory.createLabel();
        addToTable(label, COLUMN_2_WIDTH);
        return label;
    }

    private void addToTable(TUiElement element, int width){
        table.addElement(element)
            .width(width)
            .height(ROW_HEIGHT);
    }

    private void resetCostLabels() {
        for (TLabel label : buildingToLabelMap.values()) {
            label.text("0");
        }
    }
}
