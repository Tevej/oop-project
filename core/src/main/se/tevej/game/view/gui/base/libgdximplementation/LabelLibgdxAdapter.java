package main.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.se.tevej.game.view.gui.base.TLabel;

public class LabelLibgdxAdapter extends Label implements TLabel {
    public LabelLibgdxAdapter(Skin skin) {
        super("", skin);
    }

    @Override
    public TLabel text(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public void setColor(float red, float green, float blue, float alpha) {
        LabelStyle labelStyle = new LabelStyle(
            new BitmapFont(), new Color(red, green, blue, alpha));
        this.setStyle(labelStyle);
    }
}
