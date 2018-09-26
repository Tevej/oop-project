package se.tevej.game.view.rendering.ui;

public interface TTextField extends TUIElement {

    TTextField set(String text);
    TTextField addListener(OnChangeListener onChangeListener);

    interface OnChangeListener{
        void onChange(String value);
    }

}
