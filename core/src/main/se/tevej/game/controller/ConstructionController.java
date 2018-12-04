package main.se.tevej.game.controller;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import main.se.tevej.game.controller.input.base.CameraController;
import main.se.tevej.game.controller.input.base.OnMouseClickedListener;
import main.se.tevej.game.controller.input.base.OnMovedListener;
import main.se.tevej.game.controller.input.base.OnTappedListener;
import main.se.tevej.game.controller.input.base.TKey;
import main.se.tevej.game.controller.input.base.TKeyBoard;
import main.se.tevej.game.controller.input.base.TMouse;
import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.signals.SignalComponent;
import main.se.tevej.game.model.signals.SignalType;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gamerendering.OnBuildingSelectedToBuild;
import main.se.tevej.game.view.gamerendering.SelectedBuildingRenderer;

/**
 * It is from here all signals to build a building originates from.
 */
public class ConstructionController implements OnTappedListener,
    OnMovedListener, OnMouseClickedListener, OnBuildingSelectedToBuild {

    private ModelManager modelManager;
    private Entity worldEntity;
    private int mouseX = 0;
    private int mouseY = 0;
    private CameraController camera;
    private boolean buildingSelected;
    private BuildingType selectedBuilding;
    private SelectedBuildingRenderer buildingRenderer;
    private ViewManager viewManager;

    public ConstructionController(ModelManager modelManager,
                                  Entity worldEntity,
                                  CameraController camera,
                                  TKeyBoard keyboard,
                                  TMouse mouse,
                                  SelectedBuildingRenderer buildingRenderer,
                                  ViewManager viewManager) {
        this.viewManager = viewManager;
        buildingSelected = false;
        this.modelManager = modelManager;
        this.worldEntity = worldEntity;
        mouse.addMovedListener(this);
        mouse.addClickedListener(this);
        keyboard.addTappedListener(this);
        this.camera = camera;
        this.buildingRenderer = buildingRenderer;
    }


    private void buildConstruction(BuildingType type) {
        Entity tileAt = worldEntity.getComponent(WorldComponent.class).getTileAt(mouseX, mouseY);
        if (buildingSelected && !tileAt.getComponent(TileComponent.class).isOccupied()) {
            Entity entity = new Entity();
            entity.add(new BuildingComponent(type));
            entity.add(tileAt.getComponent(PositionComponent.class));
            entity.add(worldEntity.getComponent(WorldComponent.class));
            entity.add(new SignalComponent(SignalType.PAY_FOR_CONSTRUCTION));
            modelManager.getSignal().dispatch(entity);
        }
    }


    @Override
    public void onClicked(TMouse mouse, TKey button) {
        if (button == TKey.MOUSE_LEFT) {
            buildConstruction(selectedBuilding);
        }
    }

    @Override
    public void buildingSelectedToBuild(BuildingType buildingType) {
        buildingSelected = selectedBuilding == null || buildingType != selectedBuilding;
        selectedBuilding = buildingType;
    }

    @Override
    public void onMoved(TMouse mouse) {
        float zoom = viewManager.getZoomMultiplier();
        Vector2 v2 = camera.getScreenToWorldCoord(mouse.getX(), mouse.getY());
        mouseX = (int) v2.x;
        mouseY = (int) v2.y;
        float pixelPerTile = viewManager.getPixelPerTile();

        buildingRenderer.setPosition(
            (mouseX - camera.getCameraPosX()) * (pixelPerTile * zoom),
            (mouseY - camera.getCameraPosY()) * (pixelPerTile * zoom)
        );
    }

    @Override
    public void onTapped(TKeyBoard keyBoard, TKey button) {
        switch (button) {
            case KEY_L:
                buildConstruction(BuildingType.LUMBERMILL);
                break;
            case KEY_Q:
                buildConstruction(BuildingType.QUARRY);
                break;
            case KEY_P:
                buildConstruction(BuildingType.PUMP);
                break;
            case KEY_F:
                buildConstruction(BuildingType.FARM);
                break;
            default:
                break;
        }
    }
}
