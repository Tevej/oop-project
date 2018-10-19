package main.se.tevej.game.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import main.se.tevej.game.model.ModelManager;
import main.se.tevej.game.model.components.TileComponent;
import main.se.tevej.game.model.components.WorldComponent;

public class GameIo {

    private static final String WORLD_FILE = "world.json";

    public GameIo() {
    }

    public List<Entity> load() throws IOException {
        List<Entity> entities = new ArrayList<>();
        Gson gson = createGson();
        JsonReader reader = new JsonReader(
            new InputStreamReader(new FileInputStream(WORLD_FILE), StandardCharsets.UTF_8)
        );
        reader.beginArray();
        while (reader.hasNext()) {
            entities.add(gson.fromJson(reader, Entity.class));
        }
        reader.endArray();
        reader.close();
        return entities;
    }

    public void save(ModelManager modelManager) throws IOException {
        Gson gson = createGson();

        JsonWriter writer = new JsonWriter(
            new OutputStreamWriter(new FileOutputStream(WORLD_FILE), StandardCharsets.UTF_8)
        );
        writer.setIndent("  ");
        writer.beginArray();
        for (Entity entity : modelManager.getEngine().getEntities()) {
            if (entity.getComponent(TileComponent.class) != null
                || entity.getComponent(WorldComponent.class) != null) {
                continue;
            }
            gson.toJson(entity, Entity.class, writer);
        }
        writer.endArray();
        writer.close();
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Entity.class, new EntityDeserializer());
        gsonBuilder.registerTypeAdapter(Entity.class, new EntitySerializer());
        return gsonBuilder.create();
    }
}
