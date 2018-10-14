package main.se.tevej.game.view.gui;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;

import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TTable;


public class BuildingGui {
    private RenderingFactory renderingFactory;
    private TTable buildingTable;
    private LinkedList<TButton> buildingButtonList;
    private final int imageSize = 32;
    private List<OnBuildingSelectedToBuild> onBuildingSelectedToBuildList;

    public BuildingGui(RenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;

        this.onBuildingSelectedToBuildList = new LinkedList<>();
        buildingButtonList = new LinkedList<>();
        buildingButtonList.add(
            createBuildingButton(BuildingType.PUMP, "buildings/pump.png"));
        buildingButtonList.add(
            createBuildingButton(BuildingType.LUMBERMILL, "buildings/lumberMill.jpg"));
        buildingButtonList.add(
            createBuildingButton(BuildingType.QUARRY, "buildings/quarry.jpg"));
        buildingButtonList.add(
            createBuildingButton(BuildingType.HOME, "buildings/home.jpg"));

        buildingTable = renderingFactory.createTable()
            .setXPosition(Gdx.graphics.getWidth() - (imageSize / 2f))
            .setYPosition(Gdx.graphics.getHeight() / 2f)
            .grid(1, 32)
            .debug(true);

        for (TButton buildingButton : buildingButtonList) {
            buildingTable.addElement(buildingButton).width(imageSize).height(imageSize);
        }
    }

    private TButton createBuildingButton(BuildingType buildingType, String imgPath) {
        return renderingFactory.createButton().image(imgPath).addListener(new OnClickedListener() {
            @Override
            public void onClicked(TKey button) {
                for (OnBuildingSelectedToBuild onBuildingSelectedToBuild :
                    onBuildingSelectedToBuildList) {
                    onBuildingSelectedToBuild.buildingSelectedToBuild(buildingType);
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

    public void addSelectedListener(OnBuildingSelectedToBuild onBuildingSelectedToBuild) {
        onBuildingSelectedToBuildList.add(onBuildingSelectedToBuild);
    }
}
