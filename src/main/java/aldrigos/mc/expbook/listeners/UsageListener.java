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

        int storedExp = ExpBook.getSavedExp(player.getItemInHand());
        if (storedExp > 0) {
            player.getInventory().remove(player.getItemInHand());
            player.giveExp(storedExp);
            player.sendMessage(ChatColor.GOLD + "[ExpBook]You gained " + storedExp + " exp!" + ChatColor.RESET);
        }
    }
}
