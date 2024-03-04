package fr.mine;

import fr.mine.commands.StaffCommands;
import fr.mine.listeners.Lobby;
import fr.mine.managers.GameManager;
import fr.mine.players.CustomPlayer;
import fr.mine.players.CustomPlayerManager;
import fr.mine.players.PlayersScoreboard;
import fr.mine.players.RegisterPlayer;
import fr.mine.roles.RoleManager;
import fr.mine.utils.AllUtils;
import fr.mine.utils.CustomPlayerSerializationManager;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class Main extends JavaPlugin {

    @Getter
    private static Main INSTANCE;

    @Getter
    private CustomPlayerSerializationManager playerSerializationManager;

    File file;
    @Override
    public void onLoad() {
        INSTANCE = this;
        AllUtils.createPluginFile();
        saveDefaultConfig();

        file = new File(this.getDataFolder(), "players" + File.separator);
        this.playerSerializationManager = new CustomPlayerSerializationManager();

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File playersFolder = new File(getDataFolder(), "players");
        if (!playersFolder.exists()) {
            playersFolder.mkdir();
        }

        new AllUtils();
        new CustomPlayerManager();
        new RoleManager();
        new GameManager();
        new PlayersScoreboard();
    }

    @Override
    public void onDisable() {
        for(UUID uuid : CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().keySet()){
            CustomPlayer player = CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().get(uuid);
            player.saveCustomPlayer(file, playerSerializationManager, CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().get(uuid));
            player.quit();
            player.getPlayer(player.getUuid()).kickPlayer("Le serveur redemarre");
        }
    }

    @Override
    public void onEnable() {

        AllUtils.placeStructure("spawn1", new Location(getServer().getWorld("world"), -4, 200, -4));

        Objects.requireNonNull(getCommand("test")).setExecutor(new StaffCommands());
        getServer().getPluginManager().registerEvents(new Lobby(), this);
        getServer().getPluginManager().registerEvents(new RegisterPlayer(this), this);
    }

    public void setWorldSpawn(World world, double x, double y, double z) {
        Location location = new Location(world, x, y, z);
        world.setSpawnLocation(location);
    }
}