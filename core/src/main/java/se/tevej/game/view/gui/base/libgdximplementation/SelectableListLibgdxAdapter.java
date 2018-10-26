package main.java.se.tevej.game.view.gui.base.libgdximplementation;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import main.java.se.tevej.game.view.gui.base.TSelectableList;

public class SelectableListLibgdxAdapter extends ScrollPane implements TSelectableList {

    private List<String> list;

    public SelectableListLibgdxAdapter(List<String> list, Skin skin) {
        super(list, skin);
        this.list = list;
    }


    @Override
    public TSelectableList items(String... items) {
        list.setItems(items);
        return this;
    }

    @Override
    public TSelectableList addListener(SelectedChangeListener changeListener) {
        super.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeListener.onChange(list.getSelected());
            }
        });
        return this;
    }
}
