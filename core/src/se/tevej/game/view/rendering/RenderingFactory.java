package se.tevej.game.view.rendering;

import se.tevej.game.view.rendering.ui.*;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();
    TCamera createCamera();
    TTexture createTexture(String path);

    TButton createButton();
    TLabel createLabel();
    TTable createTable();
    TTextField createTextField();
    TSelectableList createSelectableList();

}
