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
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TTable;

import java.util.LinkedList;


public class BuildingGui {
    private TTable buildingTable;
    private LinkedList<TButton> buildingButtonList;

    private final int IMAGE_SIZE = 32;

    public BuildingGui(RenderingFactory renderingFactory, ConstructionController constructionController) {
        buildingButtonList = new LinkedList<>();
        buildingButtonList.add(renderingFactory.createButton().image("buildings/pump.png").addListener(new OnClickedListener() {
            @Override
            public void onClicked(TKey button) {
                constructionController.onButtonClicked(button, BuildingType.PUMP);
            }
        }));

        buildingTable = renderingFactory.createTable()
                .x(Gdx.graphics.getWidth() - (IMAGE_SIZE / 2f))
                .y(Gdx.graphics.getHeight() / 2f)
                .grid(1, 32)
                .debug(true);

        for (TButton buildingButton : buildingButtonList) {
            buildingTable.addElement(buildingButton).height(IMAGE_SIZE).width(IMAGE_SIZE);
        }
    }

    public void render() {
        buildingTable.render();
    }

    public void update(float deltaTime) {
        buildingTable.update(deltaTime);
    }
}
