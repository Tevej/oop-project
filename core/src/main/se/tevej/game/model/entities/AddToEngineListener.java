package main.se.tevej.game.model.entities;

import com.badlogic.ashley.core.Entity;

/**
 * It is, to avoid a dependency on modelManager and make it clearer why the modelManager is used, that ModelManager implements this inteface.
 */
public interface AddToEngineListener {
    void addEntityToEngine(Entity entity);
}
