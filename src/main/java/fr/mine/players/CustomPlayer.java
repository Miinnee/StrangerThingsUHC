package fr.mine.players;

import fr.mine.managers.Game;
import fr.mine.managers.GameManager;
import fr.mine.roles.Role;
import fr.mine.utils.CustomBoard;
import fr.mine.utils.CustomPlayerSerializationManager;
import fr.mine.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class CustomPlayer {

    UUID uuid;
    Role role;
    double maxHeal = 10;
    List<CustomPlayer> gameWhitelist;
    transient CustomBoard scoreboard;

    public CustomPlayer(UUID uuid){
        this.uuid = uuid;
    }

    public Player getPlayer(UUID uuid){
        return Bukkit.getPlayer(uuid);
    }

    public void join(){
        if(!CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().containsKey(uuid)){
            CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().put(uuid, this);
            initScoreboard(this);
        }
    }

    public void quit(){
        if(CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().containsKey(uuid)){
            CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().remove(uuid, this);
        }
    }

    public void initScoreboard(CustomPlayer customPlayer){
        scoreboard = new CustomBoard(customPlayer.getPlayer(customPlayer.getUuid()));
        scoreboard.updateTitle(" - Stranger Things UHC - ");
        updateScoreboard();
    }

    Game game = GameManager.getINSTANCE().getGameHashMap().get(this);

    public void updateScoreboard(){
        if(GameManager.getINSTANCE().getGameHashMap().get(this).getHost().equals(this)){
            this.scoreboard.updateLines(
                    "§f ",
                    " §7| §fWhitelist : " + game.getWhitelist(),
                    " §7| §fEtat de L'host : " + game.getEtatHost().name(),
                    " §7| §fEtat : " + game.getEtat().name(),
                    " ",
                    " §7| §fPlayers : " + Bukkit.getOnlinePlayers().size()
            );
        }


    }

    public void saveCustomPlayer(File saveDir, CustomPlayerSerializationManager customPlayerSerializationManager, CustomPlayer customPlayer){
        File file = new File(saveDir, Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName() + ".json");

        String json = customPlayerSerializationManager.serialize(customPlayer);
        FileUtils.save(file, json);
    }

}
