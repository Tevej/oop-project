package main.se.tevej.game.view.gui.base.libgdximplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import main.se.tevej.game.view.gui.base.InputProcessorListener;
import main.se.tevej.game.view.gui.base.TCell;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TUiElement;

public class TableLibgdxAdapter extends Table implements TTable {

    private Stage stage;
    private Map<Cell, TUiElement> cells;
    private boolean positionUpdated;

    public TableLibgdxAdapter(Skin skin, InputProcessorListener listener) {
        super(skin);
        stage = new Stage();
        cells = new LinkedHashMap<>();
        listener.addGameRenderingInputProcessor(stage);
        stage.addActor(this);
    }

    @Override
    public TCell addElement(TUiElement element) {
        CellLibgdxAdapter cellLibgdxAdapter = new CellLibgdxAdapter();
        cellLibgdxAdapter.setCell(getAndSetFirstAvailableCell(element));
        super.pack();
        positionUpdated = false;
        return cellLibgdxAdapter;
    }

    @Override
    public TTable backgroundColor(float red, float green, float blue, float alpha) {
        super.setBackground(
            new TextureRegionDrawable(
                new TextureRegion(
                    createSolidTexture(
                        new Color(red, green, blue, alpha)
                    )
                )
            )
        );
        return this;
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
        if (!positionUpdated) {
            positionX(super.getX() - super.getWidth() / 2);
            positionY(super.getY() - super.getHeight() / 2);
            super.invalidateHierarchy();
            positionUpdated = true;
        }
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

    private Texture createSolidTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public TTable alignLeft() {
        super.left();
        return this;
    }

    @Override
    public TTable alignCenter() {
        super.center();
        return this;
    }

    @Override
    public TTable setPadding(float amount) {
        super.pad(amount);
        return this;
    }
}
