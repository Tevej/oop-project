package main.se.tevej.game.model.components;

import static org.junit.Assert.assertEquals;

import main.se.tevej.game.model.components.buildings.HomeComponent;
import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;
import org.junit.Test;

public class TestHomeComponent {

    public TestHomeComponent() {
        super();
    }

    @Test
    public void testMethod() {
        HomeComponent homeC = new HomeComponent(new Resource(5, ResourceType.MAXPOPULATION));

        assertEquals(homeC.getMaxPopulation(), 5, 0);
        assertEquals(homeC.getCurrentPopulation(), 0, 0);
        homeC.updateCurrentPopulation(5);
        assertEquals(homeC.getCurrentPopulation(), 5, 0);
    }
}
