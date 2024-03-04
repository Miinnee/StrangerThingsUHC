package fr.mine.players;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class CustomPlayerManager {

    @Getter
    private static CustomPlayerManager INSTANCE;

    public CustomPlayerManager(){
        INSTANCE = this;
    }

    @Getter
    HashMap<UUID, CustomPlayer> customPlayerHashMap = new HashMap<>();


}
