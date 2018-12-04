package main.se.tevej.game.model.systems;

import com.badlogic.ashley.core.EntityListener;

/**
 * A inteface for the modelmanager when a system needs to create entities and
 * add them to the engine.
 */
public interface EntityCreator {
    void addEntityListener(EntityListener listener);
}
