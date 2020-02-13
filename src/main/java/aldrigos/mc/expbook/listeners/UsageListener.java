package aldrigos.mc.expbook.listeners;

import aldrigos.mc.expbook.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class UsageListener implements Listener {
    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPermission("expbook.use")){
            player.sendMessage(ChatColor.RED + "[ExpBook]You don't have permission!" + ChatColor.RESET);
            return;
        }

        int storedLvl = ExpBookItem.getSavedExp(player.getItemInHand());
        if (storedLvl > 0) {
            player.getInventory().remove(player.getItemInHand());
            player.setLevel(player.getLevel() + storedLvl);
            player.sendMessage(ChatColor.GOLD + "[ExpBook]You gained " + storedLvl + " level(s)!" + ChatColor.RESET);
        }
    }
}
