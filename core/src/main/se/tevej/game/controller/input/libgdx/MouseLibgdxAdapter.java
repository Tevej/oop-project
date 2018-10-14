package main.se.tevej.game.controller.input.libgdx;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.badlogic.gdx.Input.Buttons.MIDDLE;
import static com.badlogic.gdx.Input.Buttons.RIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.controller.input.listeners.OnDraggedListener;
import main.se.tevej.game.controller.input.listeners.OnMovedListener;

public class MouseLibgdxAdapter implements TMouse {

    private static final Map<Integer, TKey> INPUT_MAP;

    static {
        INPUT_MAP = new HashMap<>();
        INPUT_MAP.put(LEFT, TKey.MOUSE_LEFT);
        INPUT_MAP.put(RIGHT, TKey.MOUSE_RIGHT);
        INPUT_MAP.put(MIDDLE, TKey.MOUSE_MIDDLE);
    }

    public MouseLibgdxAdapter() {
        super();
    }

    @Override
    public void addDraggedListener(OnDraggedListener onDraggedListener) {
        OrderedInputMultiplexer.getInstance().add(TMouse.class, new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(TKey.MOUSE_LEFT, screenX, screenY);
                return false;
            }
        });
    }

    @Override
    public void addClickedListener(OnClickedListener onClickedListener) {
        OrderedInputMultiplexer.getInstance().add(TMouse.class, new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                onClickedListener.onClicked(INPUT_MAP.get(button));
                return false;
            }
        });
    }

    @Override
    public void addMovedListener(OnMovedListener onMovedListener) {
        TMouse mouse = this;
        OrderedInputMultiplexer.getInstance().add(TMouse.class, new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                onMovedListener.onMoved(mouse);
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
