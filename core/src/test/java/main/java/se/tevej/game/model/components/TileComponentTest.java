package main.java.se.tevej.game.model.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.badlogic.ashley.core.Entity;

import org.junit.Test;

class TileComponentTest {

    public TileComponentTest() { super(); }

    @Test
    public void occupant() {
        TileComponent t = new TileComponent();

        assertNull(t.getOccupier());
        assertFalse(t.isOccupied());
        Entity e = new Entity();
        t.occupy(e);
        assertEquals(t.getOccupier(), e);
        assertTrue(t.isOccupied());
    }
}