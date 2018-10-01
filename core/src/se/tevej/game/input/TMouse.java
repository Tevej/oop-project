package se.tevej.game.input;

public interface TMouse {

    TMouse addDraggedListener(OnDraggedListener onDraggedListener);

    TMouse addClickedListener(OnClickedListener onClickedListener);

    TMouse addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

    interface OnDraggedListener {
        void onDragged(TMouse mouse, MouseButton button, float x, float y );
    }

    interface OnClickedListener {
        void onClicked(TMouse mouse, MouseButton button);
    }

    interface OnMovedListener {
        void onMoved(TMouse mouse);
    }

    enum MouseButton{
        LEFT, MIDDLE, RIGHT;
    }


}
