package main.se.tevej.game.view.gui.base.libgdximplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import main.se.tevej.game.view.gui.base.InputProcessorListener;
import main.se.tevej.game.view.gui.base.TCell;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TUiElement;

public class TableLibgdxAdapter extends Table implements TTable {
    private Stage stage;
    private Map<Cell, TUiElement> cells;

    public TableLibgdxAdapter(InputProcessorListener listener) {
        super();
        stage = new Stage();
        cells = new LinkedHashMap<>();
        listener.addGameRenderingInputProcessor(stage);
        stage.addActor(this);
    }

    @Override
    public TCell addElement(TUiElement element) {
        CellLibgdxAdapter cellLibgdxAdapter = new CellLibgdxAdapter();
        cellLibgdxAdapter.setCell(getAndSetFirstAvailableCell(element));
        return cellLibgdxAdapter;
    }

    @Override
    public TTable positionX(float x) {
        super.setX(x);
        return this;
    }

    @Override
    public TTable positionY(float y) {
        super.setY(y);
        return this;
    }

    @Override
    public TTable grid(int rows, int columns) {
        super.clearChildren();
        cells.clear();

        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows - 1; row++) {
                cells.put(super.add(), null);
            }
            Cell cell = super.add();
            cell.row();
            cells.put(cell, null);
        }
        return this;
    }

    @Override
    public TTable debug(boolean debug) {
        super.setDebug(debug);
        return this;
    }

    @Override
    public void update(float deltaTime) {
        stage.act(deltaTime);
    }

    @Override
    public void render() {
        stage.draw();
    }

    private Cell getAndSetFirstAvailableCell(TUiElement element) {
        Cell cell = null;
        for (Map.Entry<Cell, TUiElement> entry : cells.entrySet()) {
            if (entry.getValue() == null) {
                cell = entry.getKey();
                cells.put(cell, element);
                Actor actor = (Actor) element;
                cell.setActor(actor);
                break;
            }
        }

        return cell;
    }

}
