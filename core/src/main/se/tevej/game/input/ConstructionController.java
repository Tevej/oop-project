package main.se.tevej.game.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import main.se.tevej.game.controller.input.CameraController;
import main.se.tevej.game.controller.input.TKeyBoard;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TButton;
import main.se.tevej.game.controller.input.listenerInterfaces.OnMovedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnTappedListener;
import main.se.tevej.game.libgdx.view.rendering.input.InputLibgdxFactory;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.ashley.SignalComponent;
import main.se.tevej.game.model.ashley.SignalType;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.WorldComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;

public class ConstructionController implements OnTappedListener, OnMovedListener {

    private EntityManager em;
    private Entity worldEntity;
    private int mouseX = 0;
    private int mouseY = 0;
    private TMouse mouse;
    private TKeyBoard keyboard;
    private CameraController camera;

    public ConstructionController(EntityManager em, InputLibgdxFactory factory, Entity worldEntity, CameraController camera) {
        this.em = em;
        this.worldEntity = worldEntity;
        this.keyboard = factory.createKeyBoard();
        this.mouse = factory.createMouse();
        mouse.addMovedListener(this);
        keyboard.addTappedListener(this);
        this.camera = camera;
    }

    public void onTapped (TKeyBoard keyBoard, TButton button){
        if (button.equals(TButton.KEY_L)) {
            Entity lumbermill = new Entity();
            lumbermill.add(new BuildingComponent(BuildingType.LUMBERMILL));
            buildConstruction(lumbermill);
            em.getSignal().dispatch(lumbermill);
        } else if (button.equals(TButton.KEY_Q)) {
            Entity quarry = new Entity();
            quarry.add(new BuildingComponent(BuildingType.QUARRY));
            buildConstruction(quarry);
            em.getSignal().dispatch(quarry);
        } else if (button.equals(TButton.KEY_P)) {
            Entity pump = new Entity();
            pump.add(new BuildingComponent(BuildingType.PUMP));
            buildConstruction(pump);
            em.getSignal().dispatch(pump);
        }
    }

    private void buildConstruction(Entity entity){
        entity.add(worldEntity.getComponent(WorldComponent.class).getTileAt(mouseX, mouseY).getComponent(PositionComponent.class));
        entity.add(worldEntity.getComponent(WorldComponent.class));
        entity.add(new SignalComponent(SignalType.PAYFORCONSTRUCTION));
    }

    public void onMoved(TMouse mouse) {
        Vector2 v2 = camera.getScreenToWorldCoord(mouse.getX(), mouse.getY());
        mouseX = (int)v2.x;
        mouseY = (int)v2.y;
    }


}
