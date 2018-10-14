package main.se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import main.se.tevej.game.controller.input.enums.TKey;
import main.se.tevej.game.controller.input.listenerInterfaces.OnClickedListener;
import main.se.tevej.game.libgdx.view.rendering.input.OrderedInputMultiplexer;
import main.se.tevej.game.view.rendering.ui.TButton;

public class ButtonLibgdxAdapter extends ImageTextButton implements TButton {
    private Skin skin;

    public ButtonLibgdxAdapter(Skin skin) {
        super("", skin.get(ImageTextButtonStyle.class));
        this.skin = skin;
    }

    @Override
    public TButton image(String path) {
        TextureRegionDrawable img = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path))));
        //new ImageTextButtonStyle is used to make a copy from our skin.
        ImageTextButtonStyle style = new ImageTextButtonStyle(this.skin.get(ImageTextButtonStyle.class));
        style.up = style.over = img;

        super.setStyle(style);
        return this;
    }

    @Override
    public TButton text(String text) {
        super.setText(text);
        return this;
    }

    @Override
    public TButton addListener(OnClickedListener onClickListener) {
        super.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onClickListener.onClicked(TKey.MOUSE_LEFT);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        return this;
    }

}
