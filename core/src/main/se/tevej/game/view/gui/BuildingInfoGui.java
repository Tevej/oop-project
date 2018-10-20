package main.se.tevej.game.view.gui;

import com.badlogic.gdx.Gdx;
import main.se.tevej.game.model.components.buildings.BuildingCostUtils;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.view.gamerendering.base.TTexture;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TTable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingInfoGui {
    private static final int ROWHEIGHT = 40;
    private static final int COLUMNWIDTH_1 = 100;
    private static final int COLUMNWIDTH_2 = 40;
    private static final int IMAGESIZE = 40;
    private static final int PADDING = 5;

    private static final Map<BuildingType, String> buildingImageMap = new HashMap<>();
    private static final Map<BuildingType, String> buildingNameMap = new HashMap<>();

    private TTable table;

    private TLabel buildingName;
    private TImage buildingImage;
    private TLabel waterCost;
    private TLabel stoneCost;
    private TLabel woodCost;
    private TLabel populationCost;

    public BuildingInfoGui(GuiFactory guiFactory) {

        buildingImageMap.put(BuildingType.FARM, "buildings/farm.png");
        buildingImageMap.put(BuildingType.FARM_LAND, "buildings/farm_land.png");
        buildingImageMap.put(BuildingType.HOME, "buildings/home.png");
        buildingImageMap.put(BuildingType.LUMBERMILL, "buildings/lumbermill.png");
        buildingImageMap.put(BuildingType.PUMP, "buildings/pump.png");
        buildingImageMap.put(BuildingType.QUARRY, "buildings/quarry.png");

        buildingNameMap.put(BuildingType.FARM, "Farm");
        buildingNameMap.put(BuildingType.FARM_LAND, "Farm land");
        buildingNameMap.put(BuildingType.HOME, "Home");
        buildingNameMap.put(BuildingType.LUMBERMILL, "Lumber mill");
        buildingNameMap.put(BuildingType.PUMP, "Water pump");
        buildingNameMap.put(BuildingType.QUARRY, "Quarry");


        TLabel title = guiFactory.createLabel().text("Hello");
        TButton remove = guiFactory.createButton().image("remove.png");
        buildingName = guiFactory.createLabel().text("BuildingName");
        buildingImage = guiFactory.createImage().image("buildings/farm.png");
        waterCost = guiFactory.createLabel();
        stoneCost = guiFactory.createLabel();
        woodCost = guiFactory.createLabel();
        populationCost = guiFactory.createLabel();

        table = guiFactory.createTable()
            .positionX((COLUMNWIDTH_1 + COLUMNWIDTH_2) / 2f)
            .positionY((6 * ROWHEIGHT) / 2f)
            .backgroundColor(0, 0, 0, 0.8f)
            .debug(true)
            .grid(6, 2);

        table.addElement(title).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(remove).width(COLUMNWIDTH_2).height(ROWHEIGHT);
        table.addElement(buildingName).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(buildingImage).width(COLUMNWIDTH_2).height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Water: ")).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(waterCost).width(COLUMNWIDTH_2).height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Stone: ")).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(stoneCost).width(COLUMNWIDTH_2).height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Wood: ")).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(woodCost).width(COLUMNWIDTH_2).height(ROWHEIGHT);
        table.addElement(guiFactory.createLabel().text("Population: ")).width(COLUMNWIDTH_1).height(ROWHEIGHT);
        table.addElement(populationCost).width(COLUMNWIDTH_2).height(ROWHEIGHT);

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
        this.buildingName.text(buildingNameMap.get(buildingType));
        this.buildingImage.image(buildingImageMap.get(buildingType));

        resetCostLabels();

        for (Resource resource : BuildingCostUtils.getCostOfBuilding(buildingType)) {
            switch (resource.getType()) {
                case WATER:
                    waterCost.text(Double.toString(resource.getAmount()));
                    break;
                case STONE:
                    stoneCost.text(Double.toString(resource.getAmount()));
                    break;
                case WOOD:
                    woodCost.text(Double.toString(resource.getAmount()));
                    break;
                case POPULATION:
                    populationCost.text(Double.toString(resource.getAmount()));
                    break;
            }
        }
    }

    public void update(float deltaTime) {
        table.update(deltaTime);
    }
}
