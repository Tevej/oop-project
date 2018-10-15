package main.se.tevej.game.view.gui;

import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.TImage;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TUiElement;

public class InventoryElement implements TUiElement {
    private TLabel label;
    private TImage image;
    private ResourceType resourceType;

    public InventoryElement(GuiFactory guiFactory, int amount,
                            String imagePath, ResourceType resourceType) {
        this.resourceType = resourceType;
        label = guiFactory.createLabel().text(Integer.toString(amount));
        image = guiFactory.createImage().image(imagePath);
    }

    public void update(int newAmount) {
        label.text(Integer.toString(newAmount));
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
