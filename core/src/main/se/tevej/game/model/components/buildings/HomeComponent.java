package main.se.tevej.game.model.components.buildings;

import main.se.tevej.game.model.resource.Resource;
import main.se.tevej.game.model.resource.ResourceType;

public class HomeComponent extends BuildingComponent {
    private Resource population;

    public HomeComponent() {
        super(BuildingType.HOME);
        population = new Resource(0, ResourceType.POPULATION);
    }

    public double getCurrentPopulation() {
        return population.getAmount();
    }
}
