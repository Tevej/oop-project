package main.se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.se.tevej.game.view.rendering.ui.TLabel;

public class LabelLibgdxAdapter extends Label implements TLabel {
    public LabelLibgdxAdapter(Skin skin) {
        super("", skin);
    }

    @Override
    public TLabel text(String text) {
        super.setText(text);
        return this;
    }
}
