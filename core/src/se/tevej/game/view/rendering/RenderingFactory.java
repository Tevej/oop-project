package se.tevej.game.view.rendering;

import se.tevej.game.view.rendering.ui.TButton;
import se.tevej.game.view.rendering.ui.TLabel;
import se.tevej.game.view.rendering.ui.TTable;
import se.tevej.game.view.rendering.ui.TTextField;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();
    TCamera createCamera();
    TTexture createTexture(String path);

    TButton createButton();
    TLabel createLabel();
    TTable createTable();
    TTextField createTextField();

}
