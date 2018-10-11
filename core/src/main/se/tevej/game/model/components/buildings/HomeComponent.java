package main.se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;
import main.se.tevej.game.model.utils.Resource;
import main.se.tevej.game.model.utils.ResourceType;

public class HomeComponent implements Component {
    private Resource population;

    public HomeComponent() {
        population = new Resource(0, ResourceType.POPULATION);
    }

    public double getCurrentPopulation() {
        return population.getAmount();
    }
}
