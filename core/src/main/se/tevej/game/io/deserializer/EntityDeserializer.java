package main.se.tevej.game.io.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class EntityDeserializer implements JsonDeserializer<Entity> {

    public EntityDeserializer() {
    }

    @Override
    public Entity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        JsonObject jsonEntity = json.getAsJsonObject();
        JsonObject jsonComponents = jsonEntity.get("components").getAsJsonObject();
        List<Component> components = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : jsonComponents.entrySet()) {
            try {
                components.add(context.deserialize(
                    entry.getValue(), Class.forName(entry.getKey())
                ));
            } catch (ClassNotFoundException e) {
                System.out.println(
                    "Error trying to find the class: " + entry.getKey() + " in EntityDeserializer"
                );
            }
        }
        Entity entity = new Entity();
        for (Component component : components) {
            entity.add(component);
        }
        return entity;
    }
}
