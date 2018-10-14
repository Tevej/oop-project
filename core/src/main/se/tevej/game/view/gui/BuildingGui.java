package main.se.tevej.game.view.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import main.se.tevej.game.controller.input.ConstructionController;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.libgdx.view.rendering.ui.ButtonLibgdxAdapter;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.SelectedBuildingRenderer;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TTable;

import java.util.LinkedList;
import java.util.List;


public class BuildingGui {
    private RenderingFactory renderingFactory;
    private TTable buildingTable;
    private LinkedList<TButton> buildingButtonList;
    private final int IMAGE_SIZE = 32;
    private List<OnBuildingSelectedToBuild> onBuildingSelectedToBuildList;

    public BuildingGui(RenderingFactory renderingFactory) {
        this.renderingFactory = renderingFactory;

        this.onBuildingSelectedToBuildList = new LinkedList<>();
        buildingButtonList = new LinkedList<>();
        buildingButtonList.add(createBuildingButton(BuildingType.PUMP, "buildings/pump.png"));
        buildingButtonList.add(createBuildingButton(BuildingType.LUMBERMILL, "buildings/lumberMill.jpg"));
        buildingButtonList.add(createBuildingButton(BuildingType.QUARRY, "buildings/quarry.jpg"));
        buildingButtonList.add(createBuildingButton(BuildingType.HOME, "buildings/home.jpg"));

        buildingTable = renderingFactory.createTable()
                .x(Gdx.graphics.getWidth() - (IMAGE_SIZE / 2f))
                .y(Gdx.graphics.getHeight() / 2f)
                .grid(1, 32)
                .debug(true);

        for (TButton buildingButton : buildingButtonList) {
            buildingTable.addElement(buildingButton).width(IMAGE_SIZE).height(IMAGE_SIZE);
        }
    }

    private TButton createBuildingButton(BuildingType buildingType, String imgPath){
        return renderingFactory.createButton().image(imgPath).addListener(new OnClickedListener() {
            @Override
            public void onClicked(TKey button) {
                for (OnBuildingSelectedToBuild onBuildingSelectedToBuild:
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
