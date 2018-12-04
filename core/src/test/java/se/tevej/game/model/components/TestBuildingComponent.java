package se.tevej.game.model.components;

import static org.junit.Assert.assertEquals;

import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import org.junit.Test;

public class TestBuildingComponent {

    public TestBuildingComponent() {
        super();
    }

    @Test
    public void testMethod() {
        BuildingComponent buildingC = new BuildingComponent(BuildingType.HOME);

        assertEquals(buildingC.getType(), BuildingType.HOME);
    }
}
