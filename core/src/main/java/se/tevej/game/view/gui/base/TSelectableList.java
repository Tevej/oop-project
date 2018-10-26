package main.java.se.tevej.game.view.gui.base;

public interface TSelectableList extends TUiElement {

    TSelectableList items(String... items);

    TSelectableList addListener(SelectedChangeListener changeListener);

    interface SelectedChangeListener {
        void onChange(String newSelected);
    }

}
