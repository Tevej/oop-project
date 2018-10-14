package main.se.tevej.game.libgdx.view.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.se.tevej.game.libgdx.view.rendering.ui.ButtonLibgdxAdapter;
import main.se.tevej.game.libgdx.view.rendering.ui.ImageLibgdxAdapter;
import main.se.tevej.game.libgdx.view.rendering.ui.LabelLibgdxAdapter;
import main.se.tevej.game.libgdx.view.rendering.ui.SelectableListLibgdxAdapter;
import main.se.tevej.game.libgdx.view.rendering.ui.TableLibgdxAdapter;
import main.se.tevej.game.libgdx.view.rendering.ui.TextFieldLibgdxAdapter;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;
import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TImage;
import main.se.tevej.game.view.rendering.ui.TLabel;
import main.se.tevej.game.view.rendering.ui.TSelectableList;
import main.se.tevej.game.view.rendering.ui.TTable;
import main.se.tevej.game.view.rendering.ui.TTextField;

public class RenderingLibgdxFactory implements RenderingFactory {

    private static final Skin SKIN = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

    public RenderingLibgdxFactory() {
        super();
    }

    @Override
    public TBatchRenderer createBatchRenderer() {
        return new BatchRendererLibgdxAdapter();
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

    @Override
    public TImage createImage() {
        return new ImageLibgdxAdapter();
    }

}
