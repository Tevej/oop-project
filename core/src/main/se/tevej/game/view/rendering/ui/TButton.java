package main.se.tevej.game.view.rendering.ui;

public interface TButton extends TUIElement {

    TButton image(String path);
    TButton text(String text);
    TButton addListener(OnClickListener onClickListener);

    interface OnClickListener{
        void onClick();
    }

}
