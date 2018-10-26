package main.se.tevej.game.view.gui.base;

/**
 * A cell inside a table. Used to change the size of the element within the cell.
 * Only one element per cell in the table.
 */
public interface TCell {

    TCell width(float width);

    TCell height(float height);

}
