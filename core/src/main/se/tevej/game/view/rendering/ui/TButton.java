package main.se.tevej.game.view.rendering.ui;

import main.se.tevej.game.controller.input.listenerInterfaces.OnClickedListener;

public interface TButton extends TUIElement {

    TButton image(String path);
    TButton text(String text);
    TButton addListener(OnClickedListener onClickListener);

}
