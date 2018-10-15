package main.se.tevej.game.view.gamerendering.base;

import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TSelectableList;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TTextField;

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
