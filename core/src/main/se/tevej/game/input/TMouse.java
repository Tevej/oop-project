package main.se.tevej.game.input;

import main.se.tevej.game.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.input.listenerInterfaces.OnMovedListener;

public interface TMouse {

    void addDraggedListener(OnDraggedListener onDraggedListener);

    void addClickedListener(OnClickedListener onClickedListener);

    void addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
