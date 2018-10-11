package main.se.tevej.game.libgdx.view.rendering.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import main.se.tevej.game.view.rendering.ui.TCell;
import main.se.tevej.game.view.rendering.ui.TTable;
import main.se.tevej.game.view.rendering.ui.TUiElement;

public class TableLibgdxAdapter extends Table implements TTable {
    private Stage stage;
    private Map<Cell, TUiElement> cells;

    public TableLibgdxAdapter() {
        stage = new Stage();
        cells = new LinkedHashMap<>();

        if (Gdx.input.getInputProcessor() == null) {
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(stage);
            Gdx.input.setInputProcessor(inputMultiplexer);
        } else {
            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.addProcessor(stage);
        }
        stage.addActor(this);
    }

    @Override
    public TCell addElement(TUiElement element) {
        CellLibgdxAdapter cellLibgdxAdapter = new CellLibgdxAdapter();
        cellLibgdxAdapter.setCell(getAndSetFirstAvailableCell(element));
        return cellLibgdxAdapter;
    }

    @Override
    public TTable getX(float x) {
        super.setX(x);
        return this;
    }

    @Override
    public TTable getY(float y) {
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
        for (Map.Entry<Cell, TUiElement> entry : cells.entrySet()) {
            if (entry.getValue() == null) {
                Cell cell = entry.getKey();
                cells.put(cell, element);
                Actor actor = (Actor) element;
                cell.setActor(actor);
                return cell;
            }
        }
        //TODO Throw exception instead
        System.out.println("[WARN]: Table is full");
        return null;
    }

}
