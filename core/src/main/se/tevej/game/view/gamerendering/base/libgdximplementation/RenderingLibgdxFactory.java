package main.se.tevej.game.view.gamerendering.base.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.se.tevej.game.view.gamerendering.base.RenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.TTexture;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TSelectableList;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TTextField;
import main.se.tevej.game.view.gui.base.libgdximplementation.ButtonLibgdxAdapter;
import main.se.tevej.game.view.gui.base.libgdximplementation.ImageLibgdxAdapter;
import main.se.tevej.game.view.gui.base.libgdximplementation.LabelLibgdxAdapter;
import main.se.tevej.game.view.gui.base.libgdximplementation.SelectableListLibgdxAdapter;
import main.se.tevej.game.view.gui.base.libgdximplementation.TableLibgdxAdapter;
import main.se.tevej.game.view.gui.base.libgdximplementation.TextFieldLibgdxAdapter;

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
