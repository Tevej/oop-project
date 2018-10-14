package main.se.tevej.game.controller.input;

import main.se.tevej.game.controller.input.listeners.OnDraggedListener;
import main.se.tevej.game.controller.input.listeners.OnMouseClickedListener;
import main.se.tevej.game.controller.input.listeners.OnMovedListener;

public interface TMouse {

    void addDraggedListener(OnDraggedListener onDraggedListener);

    void addClickedListener(OnMouseClickedListener onClickedListener);

    void addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
