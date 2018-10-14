package main.se.tevej.game.view;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.exceptions.UnknownResourceException;
import main.se.tevej.game.model.components.NaturalResourceComponent;
import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.model.utils.ResourceType;
import main.se.tevej.game.view.rendering.RenderingFactory;
import main.se.tevej.game.view.rendering.TBatchRenderer;
import main.se.tevej.game.view.rendering.TTexture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NaturalResourceEntityRenderable extends TextureLoader implements EntityRenderable {

    HashMap<ResourceType, TTexture> resourceTextureMap;

    public NaturalResourceEntityRenderable(RenderingFactory renderingFactory) {
        super();

        List<File> files;
        try {
            files = getFilesInDir("naturalResources");
        } catch (Exception e) {
            files = new ArrayList<File>() {};
            System.out.println("Failed to load textures.");
        }

        resourceTextureMap = new HashMap<ResourceType, TTexture>(){};

        filesToMap(files, renderingFactory);
    }

    @Override
    public void render(
        float offsetX, float offsetY, TBatchRenderer batchRenderer,
        Entity entity, int pixelPerTile) throws Exception {

        NaturalResourceComponent naturalResourceC =
            entity.getComponent(NaturalResourceComponent.class);

        SizeComponent sc = entity.getComponent(SizeComponent.class);

        batchRenderer.renderTexture(resourceTextureMap.get(nrc.getType()), (pc.getX() + offsetX) * pixelPerTile,
                (pc.getY()  + offsetY) * pixelPerTile, sc.getWidth() * pixelPerTile,
                sc.getHeight() * pixelPerTile);

    }

    @Override
    void filesToMap(List<File> files, RenderingFactory renderingFactory) {
        for (final File fileEntry : files) {
            String fileName = fileEntry.getName();
            for (String fileEnding : imageTypes) {
                if (fileName.endsWith(fileEnding)) {
                    String typeName = fileName.substring(0, fileName.length() - fileEnding.length());
                    ResourceType type = ResourceType.valueOf(typeName.toUpperCase());
                    resourceTextureMap.put(type, renderingFactory.createTexture(fileEntry.getPath()));
                }
            }
        }
    }
}
