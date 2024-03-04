package fr.mine.utils.Adapter;

import com.google.gson.*;
import fr.mine.roles.Role;
import fr.mine.roles.Stats;

import java.lang.reflect.Type;

public class StatAdapter implements JsonSerializer<Stats>, JsonDeserializer<Stats> {

    @Override
    public JsonElement serialize(Stats stats, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("dm", stats.getDm());
        jsonObject.addProperty("rm", stats.getRm());
        jsonObject.addProperty("dd", stats.getDd());
        jsonObject.addProperty("rd", stats.getRd());
        return jsonObject;
    }

    @Override
    public Stats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int dm = jsonObject.get("dm").getAsInt();
        int rm = jsonObject.get("rm").getAsInt();
        int dd = jsonObject.get("dd").getAsInt();
        int rd = jsonObject.get("rd").getAsInt();
        return new Stats(dm, rm, dd, rd);
    }
}
