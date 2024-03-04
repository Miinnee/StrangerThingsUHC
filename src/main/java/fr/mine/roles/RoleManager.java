package fr.mine.roles;

import fr.mine.Main;
import fr.mine.players.CustomPlayer;
import fr.mine.players.CustomPlayerManager;
import fr.mine.roles.enums.RoleType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.*;
import java.util.*;

public class RoleManager {
    @Getter
    private static RoleManager INSTANCE;

    public RoleManager(){
        INSTANCE = this;
    }

    @Getter
    HashMap<CustomPlayer, Role> playerRoleHashMap = new HashMap<>();
    @Getter
    HashMap<UUID, CustomPlayer> customPlayerHashMap = CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap();

    public List<String> descriptionRead(Role role){
        File file = new File(Main.getINSTANCE().getDataFolder(), "descriptions.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        List<String> strings = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            String key = "roletype." + role.getRoleType() + "." + i;
            strings.add(configuration.getString(key));
        }
        return strings;
    }

    public void roleDistribution(){
        List<Role> roles = new ArrayList<>();
        List<CustomPlayer> customPlayerList = new ArrayList<>(customPlayerHashMap.values());

        for(RoleType roleType : RoleType.values()){
            roles.add(new Role(roleType));
        }

        Collections.shuffle(customPlayerList);
        Collections.shuffle(roles);

        Iterator<Role> roleIterator = roles.iterator();
        while (roleIterator.hasNext()) {
            Role role = roleIterator.next();
            if (!customPlayerList.isEmpty()) {
                CustomPlayer customPlayer = customPlayerList.remove(0);
                playerRoleHashMap.put(customPlayer, role);
                customPlayer.setRole(role);
                roleIterator.remove(); // Remove the role from the list safely+
                Bukkit.broadcastMessage(role.getName() + " " + customPlayer.getPlayer(customPlayer.getUuid()).getName());
            } else {
                break; // Break out of the loop if there are no more players
            }
        }
    }



}
