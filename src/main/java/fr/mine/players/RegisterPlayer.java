package fr.mine.players;

import fr.mine.Main;
import fr.mine.utils.CustomPlayerSerializationManager;
import fr.mine.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class RegisterPlayer implements Listener {

    private final File saveDir;
    private final Plugin plugin;


    public RegisterPlayer(Plugin plugin) {
        this.plugin = plugin;
        this.saveDir = new File(Main.getINSTANCE().getDataFolder(), "players" + File.separator);
    }

    @EventHandler
    public void joinGame(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        File file = new File(saveDir, player.getName() + ".json");

        if (file.exists()) {
            CustomPlayerSerializationManager playerSerializationManager = Main.getINSTANCE().getPlayerSerializationManager();
            String json = FileUtils.loadContent(file);

            CustomPlayer profile = playerSerializationManager.deserialize(json);

            if (profile != null) {
                profile.join();
            } else {
                player.sendMessage(ChatColor.RED + "Erreur lors de la récupération du profil du joueur.");
            }
        } else {
            CustomPlayer customPlayer = new CustomPlayer(player.getUniqueId());
            String json = Main.getINSTANCE().getPlayerSerializationManager().serialize(customPlayer);
            FileUtils.save(file, json);

            customPlayer.join();
            player.sendMessage("Profil créé avec succès ! Bienvenue sur le serveur !");
        }
    }

    @EventHandler
    public void quitGame(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        File file = new File(saveDir, player.getName() + ".json");
        CustomPlayerSerializationManager customPlayerSerializationManager = Main.getINSTANCE().getPlayerSerializationManager();
        CustomPlayer customPlayer = CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().get(player.getUniqueId());

        String json = customPlayerSerializationManager.serialize(customPlayer);
        FileUtils.save(file, json);
        customPlayer.quit();
    }

}
