package main.se.tevej.game.controller.input.base;

/**
 * A method specification for our MouseAdapters. It also makes the change of framework easier.
 */
public interface TMouse {

    void addDraggedListener(OnDraggedListener onDraggedListener);

    void addClickedListener(OnMouseClickedListener onClickedListener);

    void addMovedListener(OnMovedListener onMovedListener);

    float getX();

    float getY();

}
