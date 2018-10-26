package se.tevej.game.model.components.buildings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class BuildingComponentTest {

    public BuildingComponentTest() {
        super();
    }

    @Test
    public void testMethod() {
        BuildingComponent buildingC = new BuildingComponent(BuildingType.HOME);

        assertEquals(buildingC.getType(), BuildingType.HOME);
    }
}