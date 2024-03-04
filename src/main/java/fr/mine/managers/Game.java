package fr.mine.managers;

import fr.mine.managers.enums.Etat;
import fr.mine.managers.enums.EtatHost;
import fr.mine.players.CustomPlayer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.players.WhiteList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Game {

    Etat etat;
    EtatHost etatHost;
    CustomPlayer host;
    List<CustomPlayer> playersList;
    Boolean whitelist;
    UUID uuid;
    Boolean pvp;
    Boolean friendlyFire;

    public Game(CustomPlayer host) {
        this.etat = Etat.LOBBY;
        this.host = host;
        playersList = new ArrayList<>();
        this.uuid = UUID.randomUUID();
        this.pvp = false;
        this.friendlyFire = false;
        this.whitelist = false;
    }



    public void create(){
        if(!GameManager.getINSTANCE().getGameHashMap().containsKey(host)){
            GameManager.getINSTANCE().getGameHashMap().put(host, this);
            this.getPlayersList().add(host);
        }
    }

    public void close(){
        if(GameManager.getINSTANCE().getGameHashMap().containsKey(host)){
            GameManager.getINSTANCE().getGameHashMap().remove(host, this);
        }
    }
}
