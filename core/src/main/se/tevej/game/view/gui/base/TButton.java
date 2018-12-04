package main.se.tevej.game.view.gui.base;

/**
 * A button element, where you can add an image, text and a listener that gets called
 * when the button is clicked.
 */
public interface TButton extends TUiElement {

    TButton image(String path);

    TButton text(String text);

    TButton addListener(OnButtonClickedListener onClickListener);

}
