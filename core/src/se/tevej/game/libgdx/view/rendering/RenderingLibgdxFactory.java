package se.tevej.game.libgdx.view.rendering;

import se.tevej.game.libgdx.view.rendering.ui.ButtonLibgdxAdapter;
import se.tevej.game.libgdx.view.rendering.ui.LabelLibgdxAdapter;
import se.tevej.game.libgdx.view.rendering.ui.TableLibgdxAdapter;
import se.tevej.game.libgdx.view.rendering.ui.TextFieldLibgdxAdapter;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;
import se.tevej.game.view.rendering.ui.TButton;
import se.tevej.game.view.rendering.ui.TLabel;
import se.tevej.game.view.rendering.ui.TTable;
import se.tevej.game.view.rendering.ui.TTextField;

public class RenderingLibgdxFactory implements RenderingFactory {

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
        return new ButtonLibgdxAdapter();
    }

    @Override
    public TLabel createLabel() {
        return new LabelLibgdxAdapter("", null);
    }

    @Override
    public TTable createTable() {
        return new TableLibgdxAdapter();
    }

    @Override
    public TTextField createTextField() {
        return new TextFieldLibgdxAdapter("", null);
    }
}
