package main.se.tevej.game.view.gui;

import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TUiElement;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class InventoryElement implements TUiElement {
    private TLabel label;
    private TImage image;
    private ResourceType resourceType;

    public InventoryElement(GuiFactory guiFactory, int amount,
                            String imagePath, ResourceType resourceType) {
        this.resourceType = resourceType;
        label = guiFactory.createLabel().text(parseDoubleWithSuffix(amount));
        image = guiFactory.createImage().image(imagePath);
    }

    public void update(int newAmount) {
        label.text(parseDoubleWithSuffix(newAmount));
    }

    private String parseDoubleWithSuffix(double amount) {
        double result;
        String suffix = "";
        if (amount < 1000) {
            result = amount;
        } else if (amount < 1000 * 1000) {
            result = amount / 1000;
            suffix = "K";
        } else {
            result = amount / (1000 * 1000);
            suffix = "M";
        }
        return round(result) + suffix;
    }

    private String round(double number) {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(number);
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public TImage getImage() {
        return image;
    }

    public TLabel getLabel() {
        return label;
    }
}
