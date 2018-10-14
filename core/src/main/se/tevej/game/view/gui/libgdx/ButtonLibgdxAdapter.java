package main.se.tevej.game.view.gui.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import main.se.tevej.game.view.rendering.ui.TButton;

public class ButtonLibgdxAdapter extends ImageTextButton implements TButton {
    public ButtonLibgdxAdapter(Skin skin) {
        super("", skin);
    }

    @Override
    public TButton image(String path) {
        TextureRegionDrawable img = new TextureRegionDrawable(
            new TextureRegion(new Texture(Gdx.files.internal(path))));
        super.getStyle().imageUp = img;
        super.getStyle().imageDown = img;
        return this;
    }

    @Override
    public TButton text(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public TButton addListener(OnClickListener onClickListener) {
        super.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                onClickListener.onClick();
            }
        });
        return this;
    }
}
