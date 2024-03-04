package fr.mine.utils.Adapter;

import com.google.gson.*;
import fr.mine.roles.enums.Camps;
import fr.mine.roles.Role;
import fr.mine.roles.enums.RoleType;
import fr.mine.roles.Stats;

import java.lang.reflect.Type;
import java.util.List;

public class RoleAdapter implements JsonSerializer<Role>, JsonDeserializer<Role> {

    @Override
    public JsonElement serialize(Role role, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("roleType", role.getRoleType().toString());
        jsonObject.addProperty("name", role.getName());
        jsonObject.addProperty("camps", role.getCamps().toString());
        jsonObject.add("stats", context.serialize(role.getStats()));
        jsonObject.add("description", context.serialize(role.getDescription()));
        return jsonObject;
    }

    @Override
    public Role deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        RoleType roleType = RoleType.valueOf(jsonObject.get("roleType").getAsString());
        Role role = new Role(roleType);
        role.setName(jsonObject.get("name").getAsString());
        role.setCamps(Camps.valueOf(jsonObject.get("camps").getAsString()));
        role.setStats(context.deserialize(jsonObject.get("stats"), Stats.class));
        List<String> description = context.deserialize(jsonObject.get("description"), List.class);
        role.setDescription(description);
        return role;
    }
}
