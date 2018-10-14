package main.se.tevej.game.controller.input.libgdx;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Buttons.LEFT;
import static com.badlogic.gdx.Input.Buttons.MIDDLE;
import static com.badlogic.gdx.Input.Buttons.RIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import main.se.tevej.game.controller.input.TMouse;
import main.se.tevej.game.controller.input.enums.TButton;
import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.controller.input.listeners.OnDraggedListener;
import main.se.tevej.game.controller.input.listeners.OnMovedListener;

public class MouseLibgdxAdapter extends InputLibgdxAdapter implements TMouse {

    private static final Map<Integer, TButton> INPUT_MAP = new HashMap<>();

    static {
        Map<Integer, TButton> map = INPUT_MAP;
        map.put(LEFT, TButton.MOUSE_LEFT);
        map.put(RIGHT, TButton.MOUSE_RIGHT);
        map.put(MIDDLE, TButton.MOUSE_MIDDLE);

    }

    public MouseLibgdxAdapter() {
        super();
    }

    @Override
    public void addDraggedListener(OnDraggedListener onDraggedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(mouse, TButton.MOUSE_LEFT, screenX, screenY);
                return true;
            }

        });
    }

    @Override
    public void addClickedListener(OnClickedListener onClickedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                onClickedListener.onClicked(mouse, INPUT_MAP.get(button));
                return true;
            }
        });
    }

    @Override
    public void addMovedListener(OnMovedListener onMovedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                onMovedListener.onMoved(mouse);
                return true;
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
