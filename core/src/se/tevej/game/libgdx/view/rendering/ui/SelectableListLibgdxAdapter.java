package se.tevej.game.libgdx.view.rendering.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import se.tevej.game.view.rendering.ui.TSelectableList;

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
    public TSelectableList addListener(SelectedChangeListener selectedChangeListener) {
        super.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectedChangeListener.onChange(list.getSelected());
            }
        });
        return this;
    }
}
