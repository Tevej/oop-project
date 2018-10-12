package main.se.tevej.game.view.rendering;

import main.se.tevej.game.view.rendering.ui.TButton;
import main.se.tevej.game.view.rendering.ui.TImage;
import main.se.tevej.game.view.rendering.ui.TLabel;
import main.se.tevej.game.view.rendering.ui.TSelectableList;
import main.se.tevej.game.view.rendering.ui.TTable;
import main.se.tevej.game.view.rendering.ui.TTextField;

public interface RenderingFactory {

    TBatchRenderer createBatchRenderer();

    TTexture createTexture(String path);

    TButton createButton();

    TLabel createLabel();

    TTable createTable();

    TTextField createTextField();

    TSelectableList createSelectableList();

    TImage createImage();

}
