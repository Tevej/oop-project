package main.java.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import main.java.se.tevej.game.model.components.buildings.BuildingType;
import main.java.se.tevej.game.view.gamerendering.OnBuildingSelectedToBuild;
import main.java.se.tevej.game.view.gui.base.GuiFactory;
import main.java.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.java.se.tevej.game.view.gui.base.TButton;
import main.java.se.tevej.game.view.gui.base.TTable;
import main.java.se.tevej.game.view.gui.base.TUiElement;


public class BuildingGui {
    private static final int NUMBER_OF_ROWS = 5;
    private static final int IMAGE_SIZE = 32;
    private static final int PADDING = 5;

    private TTable buildingTable;

    private List<OnBuildingSelectedToBuild> selectedListeners;

    public BuildingGui(GuiFactory guiFactory) {
        this.selectedListeners = new LinkedList<>();
        buildingTable = guiFactory.createTable();

        initializeTable();
        populateTable(guiFactory);
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

    private void initializeTable() {
        buildingTable
            .positionX(Gdx.graphics.getWidth() - ((IMAGE_SIZE + PADDING * 2) / 2f))
            .positionY(Gdx.graphics.getHeight() / 2f)
            .grid(NUMBER_OF_ROWS, 1)
            .backgroundColor(0, 0, 0, 0.7f)
            .alignCenter()
            .padding(PADDING);
    }

    private void populateTable(GuiFactory guiFactory) {
        createBuildingButton(guiFactory, BuildingType.PUMP, "buildings/pump.png");
        createBuildingButton(guiFactory, BuildingType.LUMBERMILL, "buildings/lumbermill.png");
        createBuildingButton(guiFactory, BuildingType.QUARRY, "buildings/quarry.png");
        createBuildingButton(guiFactory, BuildingType.HOME, "buildings/home.png");
        createBuildingButton(guiFactory, BuildingType.FARM, "buildings/farm.png");
    }

    private void createBuildingButton(GuiFactory guiFactory,
                                      BuildingType buildingType, String img) {
        TButton button = guiFactory
            .createButton()
            .image(img)
            .addListener(dispatchSelectedEventOnClick(buildingType));

        addToTable(button);
    }

    private OnButtonClickedListener dispatchSelectedEventOnClick(BuildingType buildingType) {
        return new OnButtonClickedListener() {
            @Override
            public void onClicked() {
                for (OnBuildingSelectedToBuild selectedListener :
                    selectedListeners) {
                    selectedListener.buildingSelectedToBuild(buildingType);
                }
            }
        };
    }

    private void addToTable(TUiElement element) {
        buildingTable
            .addElement(element)
            .width(IMAGE_SIZE)
            .height(IMAGE_SIZE);
    }
}
