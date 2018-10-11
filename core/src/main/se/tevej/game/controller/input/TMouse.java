package main.se.tevej.game.controller.input;

import main.se.tevej.game.controller.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.controller.input.listenerInterfaces.OnMovedListener;

public interface TMouse {

    void addDraggedListener(OnDraggedListener onDraggedListener);

    void addClickedListener(OnClickedListener onClickedListener);

    void addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
