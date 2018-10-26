package main.java.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.java.se.tevej.game.view.gui.base.GuiFactory;
import main.java.se.tevej.game.view.gui.base.InputProcessorListener;
import main.java.se.tevej.game.view.gui.base.TButton;
import main.java.se.tevej.game.view.gui.base.TImage;
import main.java.se.tevej.game.view.gui.base.TLabel;
import main.java.se.tevej.game.view.gui.base.TTable;

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
    public TImage createImage() {
        return new ImageLibgdxAdapter();
    }

}
