package main.se.tevej.game.input;

import main.se.tevej.game.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import main.se.tevej.game.input.listenerInterfaces.OnMovedListener;

public interface TMouse {

    TMouse addDraggedListener(OnDraggedListener onDraggedListener);

    TMouse addClickedListener(OnClickedListener onClickedListener);

    TMouse addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
