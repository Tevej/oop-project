package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;

import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;

public class HomeComponent implements Component {
    private Resource population;

    public HomeComponent() {
        population = new Resource(5, ResourceType.POPULATION);
    }

    public double getCurrentPopulation() {
        return population.getAmount();
    }
}
