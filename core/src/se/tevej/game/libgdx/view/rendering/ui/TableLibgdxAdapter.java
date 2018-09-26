package se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import se.tevej.game.view.rendering.ui.TCell;
import se.tevej.game.view.rendering.ui.TTable;
import se.tevej.game.view.rendering.ui.TUIElement;

public class TableLibgdxAdapter extends Table implements TTable {
    private Stage stage;

    public TableLibgdxAdapter(){
        stage = new Stage();
        if(Gdx.input.getInputProcessor() == null){
            InputMultiplexer inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(stage);
            Gdx.input.setInputProcessor(inputMultiplexer);
        }else{
            InputMultiplexer inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
            inputMultiplexer.addProcessor(stage);
        }
        stage.addActor(this);
    }

    @Override
    public TCell addElement(TUIElement element) {
        Actor actor = (Actor) element;
        System.out.println(element);
        CellLibgdxAdapter cellLibgdxAdapter = new CellLibgdxAdapter();
        cellLibgdxAdapter.setCell(super.add(actor));
        return cellLibgdxAdapter;
    }

    @Override
    public TTable x(float x) {
        super.setX(x);
        return this;
    }

    @Override
    public TTable y(float y) {
        super.setY(y);
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
}
