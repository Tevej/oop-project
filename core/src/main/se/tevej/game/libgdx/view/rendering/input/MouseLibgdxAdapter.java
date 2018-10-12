package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.*;
import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnMovedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Buttons.*;

public class MouseLibgdxAdapter extends InputLibgdxAdapter implements TMouse {

    static final Map<Integer, TKey> inputMap = new HashMap<>();

    static
    {
        Map<Integer, TKey> map = inputMap;
        map.put(LEFT, TKey.MOUSE_LEFT);
        map.put(RIGHT, TKey.MOUSE_RIGHT);
        map.put(MIDDLE, TKey.MOUSE_MIDDLE);

    }


    @Override
    public void addDraggedListener(OnDraggedListener onDraggedListener) {
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(TKey.MOUSE_LEFT, screenX, screenY);
                return false;
            }

        });
    }

    @Override
    public void addClickedListener(OnClickedListener onClickedListener) {
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                  onClickedListener.onClicked(inputMap.get(button));
                return false;
            }
        });
    }

    @Override
    public void addMovedListener(OnMovedListener onMovedListener) {
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                onMovedListener.onMoved();
                return false;
            }
        });
    }

    @Override
    public float getX() {
        return Gdx.input.getX();
    }

    @Override
    public float getY() {
        return Gdx.input.getY();
    }

}
