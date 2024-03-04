package fr.mine.listeners;

import fr.mine.players.CustomPlayer;
import fr.mine.players.CustomPlayerManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lobby implements Listener {
    private final Random random = new Random();
    private final List<Material> glassColors = Arrays.asList(Material.GLASS,Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS,
            Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS,
            Material.LIME_STAINED_GLASS, Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS,
            Material.LIGHT_GRAY_STAINED_GLASS, Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS,
            Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS,
            Material.RED_STAINED_GLASS, Material.BLACK_STAINED_GLASS);

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        Location playerLocation = player.getLocation();
        Location blockBelowLocation = playerLocation.subtract(0, 1, 0);
        Block blockBelow = blockBelowLocation.getBlock();

        if(glassColors.contains(blockBelow.getType())) {
            Material randomColor = glassColors.get(random.nextInt(glassColors.size()));
            blockBelow.setType(randomColor);
        }
        CustomPlayer customPlayer = CustomPlayerManager.getINSTANCE().getCustomPlayerHashMap().get(event.getPlayer().getUniqueId());
        player.sendMessage(customPlayer.getRole().getDescription() + " " + customPlayer.getRole().getName() + " " + customPlayer.getRole().getColor() + customPlayer.getRole().getCamps());
    }


}
