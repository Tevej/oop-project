package se.tevej.game.model.components.buildings;

import com.badlogic.ashley.core.Component;

import main.se.tevej.game.model.resources.Resource;
import main.se.tevej.game.model.resources.ResourceType;

public class HomeComponent implements Component {
    private Resource population;
    private Resource maxPopulation;

    public HomeComponent(Resource maxPopulation) {
        this.maxPopulation = maxPopulation;
        population = new Resource(0, ResourceType.CURRENTPOPULATION);
    }

    public double getCurrentPopulation() {
        return population.getAmount();
    }

    public double getMaxPopulation() {
        return maxPopulation.getAmount();
    }

    public void updateCurrentPopulation(int amount) {
        population = population.updateAmount(amount);
    }
}
