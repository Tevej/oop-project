package se.tevej.game.libgdx.input;

import com.badlogic.gdx.*;
import se.tevej.game.input.TMouse;
import se.tevej.game.input.enums.TKey;
import se.tevej.game.input.listenerInterfaces.OnClickedListener;
import se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import se.tevej.game.input.listenerInterfaces.OnMovedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Buttons.*;

public class MouseLibgdxAdapter extends InputLibgdxAdapter implements TMouse {

    public static final Map<Integer, TKey> inputMap = new HashMap<>();

    static
    {
        Map<Integer, TKey> map = inputMap;
        map.put(LEFT, TKey.MOUSE_LEFT);
        map.put(RIGHT, TKey.MOUSE_RIGHT);
        map.put(MIDDLE, TKey.MOUSE_MIDDLE);

    }


    @Override
    public TMouse addDraggedListener(OnDraggedListener onDraggedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(mouse, TKey.MOUSE_LEFT, screenX, screenY);
                return true;
            }

        });

        return this;
    }

    @Override
    public TMouse addClickedListener(OnClickedListener onClickedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                  onClickedListener.onClicked(mouse, inputMap.get(button));
                return true;
            }
        });
        return this;
    }

    @Override
    public TMouse addMovedListener(OnMovedListener onMovedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                onMovedListener.onMoved(mouse);
                return true;
            }
        });
        return this;
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
