package main.se.tevej.game.controller.input;

import main.se.tevej.game.controller.input.listeners.OnClickedListener;
import main.se.tevej.game.controller.input.listeners.OnDraggedListener;
import main.se.tevej.game.controller.input.listeners.OnMovedListener;

public interface TMouse {

    void addDraggedListener(OnDraggedListener onDraggedListener);

    void addClickedListener(OnClickedListener onClickedListener);

    void addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
