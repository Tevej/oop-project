package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gamerendering.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TTable;


public class BuildingGui {
    private static final int IMAGESIZE = 32;
    private static final int PADDING = 5;
    private GuiFactory guiFactory;
    private TTable buildingTable;

    private List<OnBuildingSelectedToBuild> selectedListeners;

    public BuildingGui(GuiFactory guiFactory) {
        this.guiFactory = guiFactory;

        this.selectedListeners = new LinkedList<>();
        List<TButton> buttonList = new LinkedList<>();
        buttonList.add(
            createBuildingButton(BuildingType.PUMP, "buildings/pump.png"));
        buttonList.add(
            createBuildingButton(BuildingType.LUMBERMILL, "buildings/lumbermill.png"));
        buttonList.add(
            createBuildingButton(BuildingType.QUARRY, "buildings/quarry.png"));
        buttonList.add(
            createBuildingButton(BuildingType.HOME, "buildings/home.png"));
        buttonList.add(
            createBuildingButton(BuildingType.FARM, "buildings/farm.png"));

        buildingTable = guiFactory.createTable()
            .positionX(Gdx.graphics.getWidth() - ((IMAGESIZE + PADDING * 2) / 2f))
            .positionY(Gdx.graphics.getHeight() / 2f)
            .grid(buttonList.size(), 1)
            .backgroundColor(0,0,0,0.7f)
            .alignCenter()
            .padding(PADDING);

        for (TButton buildingButton : buttonList) {
            buildingTable.addElement(buildingButton).width(IMAGESIZE).height(IMAGESIZE);
        }
    }

    private TButton createBuildingButton(BuildingType buildingType, String img) {
        TButton button = guiFactory.createButton();

        return button.image(img).addListener(new OnButtonClickedListener() {
            @Override
            public void onClicked() {
                for (OnBuildingSelectedToBuild selectedListener :
                    selectedListeners) {
                    selectedListener.buildingSelectedToBuild(buildingType);
                }
            }
        });
    }

    public void render() {
        buildingTable.render();
    }

    public void update(float deltaTime) {
        buildingTable.update(deltaTime);
    }

    public void addSelectedListener(OnBuildingSelectedToBuild selectedListener) {
        selectedListeners.add(selectedListener);
    }
}
