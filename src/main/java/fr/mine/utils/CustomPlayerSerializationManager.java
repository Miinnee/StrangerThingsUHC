package fr.mine.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.mine.players.CustomPlayer;
import fr.mine.roles.Role;
import fr.mine.utils.Adapter.RoleAdapter;

public class CustomPlayerSerializationManager {

    private final Gson gson;

    public CustomPlayerSerializationManager() {
        this.gson = createGsonInstance();
    }

    private Gson createGsonInstance(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .registerTypeAdapter(Role.class, new RoleAdapter())
                .create();
    }

    public String serialize(CustomPlayer customPlayer){
        return this.gson.toJson(customPlayer);
    }

    public CustomPlayer deserialize(String json){
        return this.gson.fromJson(json, CustomPlayer.class);
    }

}
