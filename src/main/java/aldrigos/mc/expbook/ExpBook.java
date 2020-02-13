package aldrigos.mc.expbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public abstract class ExpBook {
    private static NamespacedKey getExpKey(){ return new NamespacedKey(ExpBookPlugin.instance, "savedExp"); }

    public static ItemStack create(int saveLevel) {
        var item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Experience Book");

        im.getPersistentDataContainer()
            .set(getExpKey(), PersistentDataType.INTEGER, saveLevel);

        var lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "This book contains exp.");
        lore.add("Amount: " + saveLevel);
        lore.add("Click to use.");
        im.setLore(lore);

        item.setItemMeta(im);
        return item;
    }

    public static int getSavedExp(ItemStack i){
        if(!i.hasItemMeta())
            return 0;

        ItemMeta im = i.getItemMeta();

        if(!im.getPersistentDataContainer().has(getExpKey(), PersistentDataType.INTEGER))
            return 0;

        return im.getPersistentDataContainer().get(getExpKey(), PersistentDataType.INTEGER);
    }

    public static int getTotalExperience(Player player) {
        int experience = 0;
        int level = player.getLevel();
        if(level >= 0 && level <= 15) {
            experience = (int) Math.ceil(Math.pow(level, 2) + (6 * level));
            int requiredExperience = 2 * level + 7;
            double currentExp = Double.parseDouble(Float.toString(player.getExp()));
            experience += Math.ceil(currentExp * requiredExperience);
            return experience;
        } else if(level > 15 && level <= 30) {
            experience = (int) Math.ceil((2.5 * Math.pow(level, 2) - (40.5 * level) + 360));
            int requiredExperience = 5 * level - 38;
            double currentExp = Double.parseDouble(Float.toString(player.getExp()));
            experience += Math.ceil(currentExp * requiredExperience);
            return experience;
        } else {
            experience = (int) Math.ceil(((4.5 * Math.pow(level, 2) - (162.5 * level) + 2220)));
            int requiredExperience = 9 * level - 158;
            double currentExp = Double.parseDouble(Float.toString(player.getExp()));
            experience += Math.ceil(currentExp * requiredExperience);
            return experience;
        }
    }
}
