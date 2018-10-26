package main.java.se.tevej.game.model.systems;

import com.badlogic.ashley.core.EntityListener;

public interface EntityCreator {
    void addEntityListener(EntityListener listener);
}
