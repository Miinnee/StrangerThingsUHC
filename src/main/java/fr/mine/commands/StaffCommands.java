package fr.mine.commands;

import fr.mine.Main;
import fr.mine.players.CustomPlayerManager;
import fr.mine.roles.RoleManager;
import fr.mine.roles.enums.RoleType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        /*File file = new File(Main.getINSTANCE().getDataFolder(), "descriptions.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        for (RoleType roleType : RoleType.values()) {
            String key = "roletype." + roleType;
            for(int i = 0; i < 5; i++){
                configuration.set(key + "." + i, "bla bla");
            }
        }

        try {
            configuration.save(file); // Sauvegarde les modifications dans le fichier YML
            sender.sendMessage("Commande effectuée avec succès");
        } catch (IOException e) {
            e.printStackTrace();
            sender.sendMessage("Une erreur est survenue lors de l'exécution de la commande");
        }*/

        RoleManager.getINSTANCE().roleDistribution();
        RoleManager.getINSTANCE().descriptionRead(RoleManager.getINSTANCE().getPlayerRoleHashMap().get(CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().get(player.getUniqueId())));
        return true;
    }

}
