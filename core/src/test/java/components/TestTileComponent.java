package components;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.components.TileComponent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestTileComponent {

    @Test
    public void occupant(){
        TileComponent t = new TileComponent();

        assertTrue(t.getOccupier() == null);
        assertTrue(!t.isOccupied());
        Entity e = new Entity();
        t.occupy(e);
        assertTrue(t.getOccupier() == e);
        assertTrue(t.isOccupied());
    }
}
