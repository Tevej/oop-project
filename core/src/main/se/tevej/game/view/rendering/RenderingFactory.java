package main.se.tevej.game.view.rendering;

import main.se.tevej.game.view.rendering.ui.*;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();
    TCamera createCamera();
    TTexture createTexture(String path);

    TButton createButton();
    TLabel createLabel();
    TTable createTable();
    TTextField createTextField();
    TSelectableList createSelectableList();
    TImage createImage();

}
