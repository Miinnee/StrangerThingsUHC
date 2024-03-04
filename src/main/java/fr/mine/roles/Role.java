package fr.mine.roles;

import fr.mine.Main;
import fr.mine.roles.enums.Camps;
import fr.mine.roles.enums.RoleType;
import lombok.ConfigurationKeys;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Role {
    RoleType roleType;
    String name;
    Camps camps;
    String color;
    List<String> description = new ArrayList<>();
    Stats stats;

    public Role(RoleType roleType) {
        this.roleType = roleType;
        camps = roleType.getCamps();
        name = roleType.getColor() + roleType.getName();
        color = roleType.getColor();
        stats = roleType.getStats();
        description = RoleManager.getINSTANCE().descriptionRead(this);
    }


}
