package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TTable;


public class BuildingGui {
    private RenderingFactory renderingFactory;
    private TTable buildingTable;
    @SuppressFBWarnings(
        value = "SS_SHOULD_BE_STATIC",
        justification = "No need to be static and checkbugs will complain if it is."
    )
    private final int imageSize = 32;
    private List<OnBuildingSelectedToBuild> selectedListeners;

    public BuildingGui(RenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;

        this.selectedListeners = new LinkedList<>();
        List<TButton> buttonList = new LinkedList<>();
        buttonList.add(
            createBuildingButton(BuildingType.PUMP, "buildings/pump.png"));
        buttonList.add(
            createBuildingButton(BuildingType.LUMBERMILL, "buildings/lumberMill.jpg"));
        buttonList.add(
            createBuildingButton(BuildingType.QUARRY, "buildings/quarry.jpg"));
        buttonList.add(
            createBuildingButton(BuildingType.HOME, "buildings/home.jpg"));

        buildingTable = renderingFactory.createTable()
            .positionX(Gdx.graphics.getWidth() - (imageSize / 2f))
            .positionY(Gdx.graphics.getHeight() / 2f)
            .grid(1, 32)
            .debug(true);

        for (TButton buildingButton : buttonList) {
            buildingTable.addElement(buildingButton).width(imageSize).height(imageSize);
        }
    }

    private TButton createBuildingButton(BuildingType buildingType, String imgPath) {
        return renderingFactory.createButton().image(imgPath).addListener(new OnClickedListener() {
            @Override
            public void onClicked(TKey button) {
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
