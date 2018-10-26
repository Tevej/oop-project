package main.se.tevej.game.view.gui.base;

public interface TButton extends TUiElement {

    TButton image(String path);

    TButton text(String text);

    TButton addListener(OnButtonClickedListener onClickListener);

}
