package se.tevej.game.input;

import se.tevej.game.input.listenerInterfaces.OnClickedListener;
import se.tevej.game.input.listenerInterfaces.OnDraggedListener;
import se.tevej.game.input.listenerInterfaces.OnMovedListener;

public interface TMouse {

    TMouse addDraggedListener(OnDraggedListener onDraggedListener);

    TMouse addClickedListener(OnClickedListener onClickedListener);

    TMouse addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
