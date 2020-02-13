package aldrigos.mc.expbook.listeners;

import aldrigos.mc.expbook.Config;
import aldrigos.mc.expbook.ExpBook;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final float playerDropExpRate;

    public PlayerDeathListener(Config conf){
        playerDropExpRate = conf.playerDropExpRate;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        int exp = (int)(ExpBook.getTotalExperience(e.getEntity()) * playerDropExpRate);
        e.getEntity().getWorld().spawn(e.getEntity().getLocation(), ExperienceOrb.class).setExperience(exp);
    }
}
