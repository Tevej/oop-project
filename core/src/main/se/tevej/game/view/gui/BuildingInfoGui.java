package main.se.tevej.game.view.gui;

import java.util.HashMap;
import java.util.Map;

import main.se.tevej.game.model.components.buildings.BuildingCostUtils;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TTable;

public class BuildingInfoGui {
    private static final int ROWHEIGHT = 40;
    private static final int COLUMNWIDTH_1 = 100;
    private static final int COLUMNWIDTH_2 = 40;
    private static final int PADDING = 5;

    private static final Map<BuildingType, String> BUILDINGIMAGEMAP = new HashMap<>();
    private static final Map<BuildingType, String> BUILDINGNAMEMAP = new HashMap<>();

    private TTable table;

    private TLabel buildingName;
    private TImage buildingImage;
    private TLabel waterCost;
    private TLabel stoneCost;
    private TLabel woodCost;
    private TLabel populationCost;

    public BuildingInfoGui(GuiFactory guiFactory) {

        BUILDINGIMAGEMAP.put(BuildingType.FARM, "buildings/farm.png");
        BUILDINGIMAGEMAP.put(BuildingType.FARM_LAND, "buildings/farm_land.png");
        BUILDINGIMAGEMAP.put(BuildingType.HOME, "buildings/home.png");
        BUILDINGIMAGEMAP.put(BuildingType.LUMBERMILL, "buildings/lumbermill.png");
        BUILDINGIMAGEMAP.put(BuildingType.PUMP, "buildings/pump.png");
        BUILDINGIMAGEMAP.put(BuildingType.QUARRY, "buildings/quarry.png");

        BUILDINGNAMEMAP.put(BuildingType.FARM, "Farm");
        BUILDINGNAMEMAP.put(BuildingType.FARM_LAND, "Farm land");
        BUILDINGNAMEMAP.put(BuildingType.HOME, "Home");
        BUILDINGNAMEMAP.put(BuildingType.LUMBERMILL, "Lumber mill");
        BUILDINGNAMEMAP.put(BuildingType.PUMP, "Water pump");
        BUILDINGNAMEMAP.put(BuildingType.QUARRY, "Quarry");

        table = guiFactory.createTable()
            .positionX((PADDING + COLUMNWIDTH_1 + COLUMNWIDTH_2 + PADDING) / 2f)
            .positionY((6 * ROWHEIGHT) / 2f + PADDING)
            .backgroundColor(0, 0, 0, 0.8f)
            .padding(PADDING)
            .grid(6, 2);

        TLabel title = guiFactory.createLabel().text("Hello");
        table.addElement(title)
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        TButton remove = guiFactory.createButton().image("remove.png");
        table.addElement(remove)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);
        buildingName = guiFactory.createLabel().text("BuildingName");
        table.addElement(buildingName)
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        buildingImage = guiFactory.createImage().image("buildings/farm.png");
        table.addElement(buildingImage)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);

        table.addElement(guiFactory.createLabel().text("Water: "))
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        waterCost = guiFactory.createLabel();
        table.addElement(waterCost)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Stone: "))
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        stoneCost = guiFactory.createLabel();
        table.addElement(stoneCost)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Wood: "))
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        woodCost = guiFactory.createLabel();
        table.addElement(woodCost)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Population: "))
            .width(COLUMNWIDTH_1)
            .height(ROWHEIGHT);
        populationCost = guiFactory.createLabel();
        table.addElement(populationCost)
            .width(COLUMNWIDTH_2)
            .height(ROWHEIGHT);
    }

    private void resetCostLabels() {
        waterCost.text("0");
        stoneCost.text("0");
        woodCost.text("0");
        populationCost.text("0");
    }

    public void render() {
        table.render();
    }

    public void updateInfo(BuildingType buildingType) {
        this.buildingName.text(BUILDINGNAMEMAP.get(buildingType));
        this.buildingImage.image(BUILDINGIMAGEMAP.get(buildingType));

        resetCostLabels();

        for (Resource resource : BuildingCostUtils.getCostOfBuilding(buildingType)) {
            switch (resource.getType()) {
                case WATER:
                    waterCost.text(Integer.toString((int) resource.getAmount()));
                    break;
                case STONE:
                    stoneCost.text(Integer.toString((int) resource.getAmount()));
                    break;
                case WOOD:
                    woodCost.text(Integer.toString((int) resource.getAmount()));
                    break;
                case POPULATION:
                    populationCost.text(Integer.toString((int) resource.getAmount()));
                    break;
                default:
                    break;
            }
        }
    }

    public void update(float deltaTime) {
        table.update(deltaTime);
    }
}
