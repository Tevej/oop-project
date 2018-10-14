package main.se.tevej.game.controller.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.libgdx.InputLibgdxFactory;
import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.controller.input.listeners.OnMovedListener;
import main.se.tevej.game.controller.input.listeners.OnTappedListener;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.SelectedBuildingRenderer;
import main.se.tevej.game.view.ViewManager;
import main.se.tevej.game.view.gui.OnBuildingSelectedToBuild;

public class ConstructionController implements OnTappedListener, OnMovedListener, OnClickedListener, OnBuildingSelectedToBuild {

    private EntityManager em;
    private Entity worldEntity;
    private int mouseX = 0;
    private int mouseY = 0;
    private TMouse mouse;
    private TKeyBoard keyboard;
    private CameraController camera;
    private boolean buildingSelected;
    private BuildingType selectedBuilding;
    private SelectedBuildingRenderer selectedBuildingRenderer;

    public ConstructionController(EntityManager em, InputLibgdxFactory factory, Entity worldEntity,
                                  CameraController camera, TKeyBoard keyboard, TMouse mouse,
                                  SelectedBuildingRenderer selectedBuildingRenderer) {
        buildingSelected = false;
        this.em = em;
        this.worldEntity = worldEntity;
        this.keyboard = keyboard;
        this.mouse = mouse;
        mouse.addMovedListener(this);
        mouse.addClickedListener(this);
        keyboard.addTappedListener(this);
        this.camera = camera;
        this.selectedBuildingRenderer = selectedBuildingRenderer;
    }

    private void buildConstruction(BuildingType type) {
        Entity entity = new Entity();
        entity.add(new BuildingComponent(type));
        entity.add(worldEntity.getComponent(WorldComponent.class)
            .getTileAt(mouseX, mouseY).getComponent(PositionComponent.class));
        entity.add(worldEntity.getComponent(WorldComponent.class));
        entity.add(new SignalComponent(SignalType.PAYFORCONSTRUCTION));
        em.getSignal().dispatch(entity);
    }

    private void buildConstruction() {
        if (buildingSelected) {
            buildingSelected = false;
            buildConstruction(selectedBuilding);
        }
    }

    @Override
    public void onClicked(TKey button) {
        System.out.println("Hej");
        if (button == TKey.MOUSE_LEFT) {
            System.out.println(button);
            buildConstruction();
        }
    }

    @Override
    public void buildingSelectedToBuild(BuildingType buildingType) {
        buildingSelected = selectedBuilding == null || buildingType != selectedBuilding;
        selectedBuilding = buildingType;
    }

    @Override
    public void onMoved(TMouse mouse) {
        Vector2 v2 = camera.getScreenToWorldCoord(mouse.getX(), mouse.getY());
        mouseX = (int) v2.x;
        mouseY = (int) v2.y;
        selectedBuildingRenderer.setPosition(
            (mouseX - camera.getCameraPosX()) * ViewManager.PIXEL_PER_TILE,
            (mouseY - camera.getCameraPosY()) * ViewManager.PIXEL_PER_TILE);
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
            default:
                break;
        }
    }
}
