package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

public class NaturalResourceEntityRenderable implements EntityRenderable {

    private TTexture water, stone, wood;


    public NaturalResourceEntityRenderable(RenderingFactory renderingFactory) {
        this.water = renderingFactory.createTexture("water.jpg");
        this.stone = renderingFactory.createTexture("stone.jpg");
        this.wood = renderingFactory.createTexture("wood.jpg");
    }

    @Override
    public void render(TBatchRenderer batchRenderer, Entity entity, int pixelPerTile) {

        NaturalResourceComponent nrc = entity.getComponent(NaturalResourceComponent.class);

        PositionComponent pc = entity.getComponent(PositionComponent.class);

        SizeComponent sc = entity.getComponent(SizeComponent.class);
        switch (nrc.getType()) {

            case WATER:
                batchRenderer.renderTexture(water, pc.getX()*pixelPerTile, pc.getY()*pixelPerTile,
                        sc.getWidth()*pixelPerTile, sc.getHeight()*pixelPerTile);
                break;
            case STONE:
                batchRenderer.renderTexture(stone, pc.getX()*pixelPerTile, pc.getY()*pixelPerTile,
                        sc.getWidth()*pixelPerTile, sc.getHeight()*pixelPerTile);
                break;
            case WOOD:
                batchRenderer.renderTexture(wood, pc.getX()*pixelPerTile, pc.getY()*pixelPerTile,
                        sc.getWidth()*pixelPerTile, sc.getHeight()*pixelPerTile);
                break;
        }
    }
}
