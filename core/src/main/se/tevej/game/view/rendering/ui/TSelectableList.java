package main.se.tevej.game.view.rendering.ui;

public interface TSelectableList extends TUiElement {

    TSelectableList items(String... items);

    TSelectableList addListener(SelectedChangeListener changeListener);

    interface SelectedChangeListener {
        void onChange(String newSelected);
    }

}
