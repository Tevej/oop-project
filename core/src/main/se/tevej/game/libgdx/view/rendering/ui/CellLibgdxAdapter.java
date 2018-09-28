package main.se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import main.se.tevej.game.view.rendering.ui.TCell;

public class CellLibgdxAdapter implements TCell {

    private Cell cell;

    public void setCell(Cell cell){
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
