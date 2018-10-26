package main.java.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

/**
 * This inteface is used to avoid a dependency on modelManager and make it clearer why the
 * modelManager is used.
 */
public interface AddToEngineListener {
    void addEntityToEngine(Entity entity);
}
