package fr.mine.utils;

import fr.mine.Main;
import fr.mine.roles.enums.Camps;
import fr.mine.roles.Role;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.entity.Player;
import org.bukkit.structure.Structure;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class AllUtils {

    @Getter
    private static AllUtils INSTANCE;

    public AllUtils() {
        INSTANCE = this;
    }

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(message)));
    }

    public void placerBloc(World world, int x, int y, int z, Material blocType) {
        if (world != null) {
            Block block = world.getBlockAt(returnloc(x, y ,z));
            block.setType(blocType);
        }
    }

    public static String activenotactive(Boolean boole){
        if (boole) {
            return "§aActivé";
        }
        return "§cDésactivé";
    }

    public static void placeStructure(String name, Location location) {
        try {
            Structure structure = Main.getINSTANCE().getServer().getStructureManager().loadStructure(new File(Main.getINSTANCE().getDataFolder(), name + ".nbt"));
            structure.place(location, false, StructureRotation.NONE, Mirror.NONE, 0, 1.0F, new Random());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DyeColor getRandomDyeColor() {
        DyeColor[] colors = DyeColor.values();
        return colors[new Random().nextInt(colors.length)];
    }

    public static void createPluginFile() {
        File dataFolder = Main.getINSTANCE().getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File configFile = new File(dataFolder, "config.yml");
        File descriptionFile = new File(dataFolder, "descriptionRole.yml");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                Main.getINSTANCE().getLogger().info("Le fichier du plugin a été créé avec succès !");
            } catch (IOException e) {
                e.printStackTrace();
                Main.getINSTANCE().getLogger().severe("Erreur lors de la création du fichier du plugin !");
            }
        }
    }

    public static String translateAlternateColorCodesAndHexes(final char altColorChar, final String textToTranslate) {
        final StringBuilder b = new StringBuilder();
        final char[] mess = textToTranslate.toCharArray();
        boolean color = false, hashtag = false, doubleTag = false;
        char tmp; // Used in loops

        for (int i = 0; i < mess.length; ) { // i increment is handled case by case for speed

            final char c = mess[i];

            if (doubleTag) { // DoubleTag module
                doubleTag = false;

                final int max = i + 3;

                if (max <= mess.length) {
                    // There might be a hex color here
                    boolean match = true;

                    for (int n = i; n < max; n++) {
                        tmp = mess[n];
                        // The order of the checks below is meant to improve performances (i.e. capital letters check is at the end)
                        if (!((tmp >= '0' && tmp <= '9') || (tmp >= 'a' && tmp <= 'f') || (tmp >= 'A' && tmp <= 'F'))) {
                            // It wasn't a hex color, appending found chars to the StringBuilder and continue the for loop
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        b.append(ChatColor.COLOR_CHAR);
                        b.append('x');

                        // Copy colors with a color code in between
                        for (; i < max; i++) {
                            tmp = mess[i];
                            b.append(ChatColor.COLOR_CHAR);
                            b.append(tmp);
                            // Double the color code
                            b.append(ChatColor.COLOR_CHAR);
                            b.append(tmp);
                        }

                        // i increment has been already done
                        continue;
                    }
                }

                b.append(altColorChar);
                b.append("##");
                // Malformed hex, let's carry on checking mess[i]
            }

            if (hashtag) { // Hashtagmodule
                hashtag = false;

                // Check for double hashtag (&##123 => &#112233)
                if (c == '#') {
                    doubleTag = true;
                    i++;
                    continue;
                }

                final int max = i + 6;

                if (max <= mess.length) {
                    // There might be a hex color here
                    boolean match = true;

                    for (int n = i; n < max; n++) {
                        tmp = mess[n];
                        // The order of the checks below is meant to improve performances (i.e. capital letters check is at the end)
                        if (!((tmp >= '0' && tmp <= '9') || (tmp >= 'a' && tmp <= 'f') || (tmp >= 'A' && tmp <= 'F'))) {
                            // It wasn't a hex color, appending found chars to the StringBuilder and continue the for loop
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        b.append(ChatColor.COLOR_CHAR);
                        b.append('x');

                        // Copy colors with a color code in between
                        for (; i < max; i++) {
                            b.append(ChatColor.COLOR_CHAR);
                            b.append(mess[i]);
                        }
                        // i increment has been already done
                        continue;
                    }
                }

                b.append(altColorChar);
                b.append('#');
                // Malformed hex, let's carry on checking mess[i]
            }


            if (color) { // Color module
                color = false;

                if (c == '#') {
                    hashtag = true;
                    i++;
                    continue;
                }

                // The order of the checks below is meant to improve performances (i.e. capital letters check is at the end)
                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || c == 'r' || (c >= 'k' && c <= 'o') || (c >= 'A' && c <= 'F') || c == 'R' || (c >= 'K' && c <= 'O')) {
                    b.append(ChatColor.COLOR_CHAR);
                    b.append(c);
                    i++;
                    continue;
                }

                b.append(altColorChar);
                // Not a valid color, let's carry on checking mess[i]
            }

            // Base case
            if (c == altColorChar) { // c == '&'
                color = true;
                i++;
                continue;
            }

            // None matched, append current character
            b.append(c);
            i++;

        }

        // Append '&' if '&' was the last character of the string
        if (color)
            b.append(altColorChar);
        else // color and hashtag cannot be true at the same time
            // Append "&#" if "&#" were the last characters of the string
            if (hashtag) {
                b.append(altColorChar);
                b.append('#');
            } else // color, hashtag, and doubleTag cannot be true at the same time
                // Append "&##" if "&##" were the last characters of the string
                if (doubleTag) {
                    b.append(altColorChar);
                    b.append("##");
                }

        return b.toString();
    }


    public String statToText(String s){
        switch(s){
            case "dm" -> {
                return "Dégats de Melée";
            }
            case "rm" -> {
                return "Resistance de Melée";
            }
            case "dd" -> {
                return "Dégats de Distance";
            }
            case "rd" -> {
                return "Resistance des Dégats Distant";
            }
            default -> {
                return "null";
            }
        }
    }


    public Location returnloc(int x, int y, int z){
        Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
        return loc;
    }

    public Location getLocationFromVector(Vector vector) {
        World world = Bukkit.getWorld("world");
        double x = vector.getX();
        double y = vector.getY();
        double z = vector.getZ();

        return new Location(world, x, y, z);
    }

    public static String getColorByRole(Role role){
        return role.getRoleType().getColor();
    }

    public static Camps getCampByRole(Role role){
        return role.getCamps();
    }



}
