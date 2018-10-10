package main.se.tevej.game.input;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.input.enums.TButton;
import main.se.tevej.game.input.listenerInterfaces.OnMovedListener;
import main.se.tevej.game.input.listenerInterfaces.OnTappedListener;
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

    public ConstructionController(EntityManager em, InputLibgdxFactory factory, Entity worldEntity) {
        this.em = em;
        this.worldEntity = worldEntity;
        this.keyboard = factory.createKeyBoard();
        this.mouse = factory.createMouse();
        mouse.addMovedListener(this);
        keyboard.addTappedListener(this);
    }

    public void onTapped (TKeyBoard keyBoard, TButton button){
        if (button.equals(TButton.KEY_L)) {
            Entity buildLumbermill = new Entity();
            buildLumbermill.add(new BuildingComponent(BuildingType.LUMBERMILL));
            buildLumbermill.add(worldEntity.getComponent(WorldComponent.class).getTileAt(mouseX, mouseY).getComponent(PositionComponent.class));
            buildLumbermill.add(worldEntity.getComponent(WorldComponent.class));
            buildLumbermill.add(new SignalComponent(SignalType.PAYFORCONSTRUCTION));
            em.getSignal().dispatch(buildLumbermill);
        }
    }

    public void onMoved(TMouse mouse) {
        mouseX = (int)mouse.getX()/32;
        mouseY = (int)mouse.getY()/32;
    }


}
