package main.se.tevej.game.libgdx.view.rendering.input;

import com.badlogic.gdx.*;
import main.se.tevej.game.input.TMouse;
import main.se.tevej.game.input.enums.TButton;
import main.se.tevej.game.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.input.listenerInterfaces.OnMovedListener;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Buttons.*;

public class MouseLibgdxAdapter extends InputLibgdxAdapter implements TMouse {

    static final Map<Integer, TButton> inputMap = new HashMap<>();

    static
    {
        Map<Integer, TButton> map = inputMap;
        map.put(LEFT, TButton.MOUSE_LEFT);
        map.put(RIGHT, TButton.MOUSE_RIGHT);
        map.put(MIDDLE, TButton.MOUSE_MIDDLE);

    }


    @Override
    public TMouse addDraggedListener(OnDraggedListener onDraggedListener) {
        final TMouse mouse = this;
        addToInputMultiplexer(new InputAdapter(){
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                onDraggedListener.onDragged(mouse, TButton.MOUSE_LEFT, screenX, screenY);
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
