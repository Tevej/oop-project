package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gamerendering.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TTable;


public class BuildingGui {
    private GuiFactory guiFactory;
    private TTable buildingTable;
    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final int IMAGESIZE = 32;
    private final int PADDING = 5;
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
            .grid(1, buttonList.size())
            .backgroundColor(0,0,0,0.7f)
            .alignCenter()
            .setPadding(PADDING)
            .debug(false);

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
