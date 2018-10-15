package main.se.tevej.game.view.gamerendering.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.badlogic.ashley.core.Entity;

import main.se.tevej.game.model.components.PositionComponent;
import main.se.tevej.game.model.components.SizeComponent;
import main.se.tevej.game.model.components.buildings.BuildingComponent;
import main.se.tevej.game.model.components.buildings.BuildingType;
import main.se.tevej.game.view.gamerendering.base.GameRenderingFactory;
import main.se.tevej.game.view.gamerendering.base.TBatchRenderer;
import main.se.tevej.game.view.gamerendering.base.TTexture;
import main.se.tevej.game.view.TextureLoader;

public class BuildingEntityRenderable extends TextureLoader implements EntityRenderable {

    private Map<BuildingType, TTexture> buildingImageMap;

    public BuildingEntityRenderable(GameRenderingFactory renderFactory) {
        super();

        List<File> files;
        try {
            files = getFilesInDir("buildings");
        } catch (Exception e) {
            files = new ArrayList<File>() {
            };
            System.out.println("Failed to load textures.");
        }

        buildingImageMap = new HashMap<BuildingType, TTexture>() {
        };

        filesToMap(files, renderFactory);
    }

    @Override
    public void render(
        float offsetX, float offsetY, TBatchRenderer batchRenderer,
        Entity entity, int pixelPerTile) throws IllegalArgumentException {

        BuildingComponent buildingC = entity.getComponent(BuildingComponent.class);
        PositionComponent posC = entity.getComponent(PositionComponent.class);
        SizeComponent sizeC = entity.getComponent(SizeComponent.class);
        float x = (posC.getX() + offsetX) * pixelPerTile;
        float y = (posC.getY() + offsetY) * pixelPerTile;
        float width = sizeC.getWidth() * pixelPerTile;
        float height = sizeC.getHeight() * pixelPerTile;
        batchRenderer.renderTexture(buildingImageMap.get(buildingC.getType()), x, y, width, height);
    }

    @Override
    public final void filesToMap(List<File> files, GameRenderingFactory renderFactory) {
        for (final File fileEntry : files) {
            String name = fileEntry.getName();
            for (String ending : imageTypes) {
                if (name.endsWith(ending)) {
                    String typeName = name.substring(0, name.length() - ending.length());
                    BuildingType type = BuildingType.valueOf(typeName.toUpperCase(Locale.ENGLISH));
                    buildingImageMap.put(type, renderFactory.createTexture(fileEntry.getPath()));
                }
            }
        }
    }
}
