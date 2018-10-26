package main.java.se.tevej.game.model.components.buildings;

import static org.junit.Assert.assertEquals;

import main.java.se.tevej.game.model.resources.Resource;
import main.java.se.tevej.game.model.resources.ResourceType;
import org.junit.Test;

class HomeComponentTest {

    public HomeComponentTest() { super(); }

    @Test
    public void testMethod() {
        HomeComponent homeC = new HomeComponent(new Resource(5, ResourceType.MAXPOPULATION));

        assertEquals(homeC.getMaxPopulation(), 5, 0);
        assertEquals(homeC.getCurrentPopulation(), 0, 0);
        homeC.updateCurrentPopulation(5);
        assertEquals(homeC.getCurrentPopulation(), 5, 0);
    }
}