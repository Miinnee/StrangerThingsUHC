package fr.mine.managers;

import fr.mine.players.CustomPlayer;
import fr.mine.players.CustomPlayerManager;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class GameManager {

    @Getter
    private static GameManager INSTANCE;

    public GameManager(){
        INSTANCE = this;
    }

    @Getter
    HashMap<CustomPlayer, Game> gameHashMap = new HashMap<>();

}
