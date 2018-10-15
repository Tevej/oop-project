package components;

import static org.junit.Assert.assertEquals;

import main.se.tevej.game.model.components.buildings.HomeComponent;

import org.junit.Test;

public class TestHomeComponent {

    public TestHomeComponent() {
        super();
    }

    @Test
    public void testMethod() {
        HomeComponent homeC = new HomeComponent();

        assertEquals(homeC.getCurrentPopulation(), 5, 0);
    }
}
