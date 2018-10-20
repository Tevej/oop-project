package main.se.tevej.game.controller;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.controller.input.base.CameraController;
import main.se.tevej.game.controller.input.base.OnMouseClickedListener;
import main.se.tevej.game.controller.input.base.TKey;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gui.BuildingInfoGui;

public class InfoController implements OnMouseClickedListener {
    private WorldComponent worldC;
    private CameraController cameraController;
    private BuildingInfoGui buildingInfoGui;

    public InfoController(
        WorldComponent worldC,
        CameraController cameraController,
        BuildingInfoGui buildingInfoGui,
        TMouse mouse
    ) {
        this.worldC = worldC;
        this.cameraController = cameraController;
        this.buildingInfoGui = buildingInfoGui;
        mouse.addClickedListener(this);
    }

    @Override
    public void onClicked(TMouse mouse, TKey key) {
        System.out.println("Mouse clicked");
        int posX = (int) cameraController.getScreenToWorldCoord(mouse.getX(), mouse.getY()).x;
        int posY = (int) cameraController.getScreenToWorldCoord(mouse.getX(), mouse.getY()).y;
        if (key == TKey.MOUSE_LEFT && worldC.isTileOccupied(posX, posY)) {
            Entity entity = worldC.getTileOccupier(posX, posY);
            try {
                BuildingComponent component = entity.getComponent(BuildingComponent.class);
                BuildingType buildingType = component.getType();
                buildingInfoGui.updateInfo(buildingType);
            } catch (NullPointerException e) {
                System.out.println("Non-building tile clicked");
            }
        }
    }
}
