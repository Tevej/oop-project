package main.java.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;

import main.java.se.tevej.game.view.gui.base.TCell;

public class CellLibgdxAdapter implements TCell {

    private Cell cell;

    public CellLibgdxAdapter() {
        super();
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public TCell width(float width) {
        cell.width(width);
        return this;
    }

    @Override
    public TCell height(float height) {
        cell.height(height);
        return this;
    }
}
