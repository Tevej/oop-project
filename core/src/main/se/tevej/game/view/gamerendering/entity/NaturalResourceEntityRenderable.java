package main.se.tevej.game.view.gamerendering.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.gamerendering.base.RenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.TTexture;
import main.se.tevej.game.view.utils.TextureLoader;

public class NaturalResourceEntityRenderable extends TextureLoader implements EntityRenderable {

    private Map<ResourceType, TTexture> resourceImageMap;

    public NaturalResourceEntityRenderable(RenderingFactory renderFactory) {
        super();

        List<File> files;
        try {
            files = getFilesInDir("naturalResources");
        } catch (Exception e) {
            files = new ArrayList<File>() {
            };
            System.out.println("Failed to load textures.");
        }

        resourceImageMap = new HashMap<ResourceType, TTexture>() {
        };

        filesToMap(files, renderFactory);
    }

    @Override
    public void render(
        float offsetX, float offsetY, TBatchRenderer batchRenderer,
        Entity entity, int pixelPerTile) throws IllegalArgumentException {

        NaturalResourceComponent naturalResourceC =
            entity.getComponent(NaturalResourceComponent.class);
        PositionComponent positionC = entity.getComponent(PositionComponent.class);

        SizeComponent sizeC = entity.getComponent(SizeComponent.class);

        batchRenderer.renderTexture(resourceImageMap.get(naturalResourceC.getType()),
            (positionC.getX() + offsetX) * pixelPerTile,
            (positionC.getY() + offsetY) * pixelPerTile,
            sizeC.getWidth() * pixelPerTile,
            sizeC.getHeight() * pixelPerTile);
    }

    @Override
    public final void filesToMap(List<File> files, RenderingFactory renderFactory) {
        for (final File fileEntry : files) {
            String name = fileEntry.getName();
            for (String ending : imageTypes) {
                if (name.endsWith(ending)) {
                    String typeName = name.substring(0, name.length() - ending.length());
                    ResourceType type = ResourceType.valueOf(typeName.toUpperCase(Locale.ENGLISH));
                    resourceImageMap.put(type, renderFactory.createTexture(fileEntry.getPath()));
                }
            }
        }
    }
}
