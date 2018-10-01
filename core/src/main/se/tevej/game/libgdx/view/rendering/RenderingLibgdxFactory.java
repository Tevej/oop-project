package main.se.tevej.game.libgdx.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import main.se.tevej.game.libgdx.view.rendering.ui.*;
import main.se.tevej.game.view.rendering.ui.*;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class RenderingLibgdxFactory implements RenderingFactory {

    private static final Skin SKIN = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

    @Override
    public TBatchRenderer createBatchRenderer() {
        return new BatchRendererLibgdxAdapter();
    }

    public CameraLibgdxAdapter createCamera() {
        return new CameraLibgdxAdapter();
    }

    @Override
    public TTexture createTexture(String path) {
        return new TextureLibgdxAdapter(path);
    }

    @Override
    public TButton createButton() {
        return new ButtonLibgdxAdapter(SKIN);
    }

    @Override
    public TLabel createLabel() {
        return new LabelLibgdxAdapter(SKIN);
    }

    @Override
    public TTable createTable() {
        return new TableLibgdxAdapter();
    }

    @Override
    public TTextField createTextField() {
        return new TextFieldLibgdxAdapter(SKIN);
    }

    @Override
    public TSelectableList createSelectableList() {
        List<String> list = new List(SKIN);
        return new SelectableListLibgdxAdapter(list, SKIN);
    }

}
