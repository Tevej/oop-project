package main.se.tevej.game.io;

import java.lang.reflect.Type;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class EntitySerializer implements JsonSerializer<Entity> {

    public EntitySerializer() {
    }

    @Override
    public JsonElement serialize(Entity entity, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonEntity = new JsonObject();

        JsonObject jsonComponents = new JsonObject();

        ImmutableArray<Component> components = entity.getComponents();
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            String componentName = component.getClass().getName();
            jsonComponents.add(componentName, context.serialize(components.get(i)));
        }

        jsonEntity.add("components", jsonComponents);

        return jsonEntity;
    }
}
