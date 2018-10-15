package main.se.tevej.game.view.rendering.ui;

import main.se.tevej.game.view.gui.libgdx.OnButtonClickedListener;

public interface TButton extends TUiElement {

    TButton image(String path);

    TButton text(String text);

    TButton addListener(OnButtonClickedListener onClickListener);

}
