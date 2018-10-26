package main.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.InputProcessorListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TSelectableList;
import main.se.tevej.game.view.gui.base.TTable;
import main.se.tevej.game.view.gui.base.TTextField;

/**
 * The libGDX implementation of the GuiFactory.
 */
public class GuiLibgdxFactory implements GuiFactory {

    private static final Skin SKIN = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));
    private InputProcessorListener processorListener;

    public GuiLibgdxFactory(InputProcessorListener listener) {
        super();
        this.processorListener = listener;
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
        return new TableLibgdxAdapter(SKIN, processorListener);
    }

    @Override
    public TTextField createTextField() {
        return new TextFieldLibgdxAdapter(SKIN);
    }

    @Override
    public TSelectableList createSelectableList() {
        List<String> list = new List<>(SKIN);
        return new SelectableListLibgdxAdapter(list, SKIN);
    }

    @Override
    public TImage createImage() {
        return new ImageLibgdxAdapter();
    }

}
