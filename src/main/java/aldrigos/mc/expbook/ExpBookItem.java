package aldrigos.mc.expbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ExpBookItem extends ItemStack {
    private static NamespacedKey getExpKey(){ return new NamespacedKey(ExpBookPlugin.instance, "savedExp"); }

    public ExpBookItem(int saveLevel) {
        super(Material.ENCHANTED_BOOK);
        ItemMeta im = getItemMeta();
        im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Experience Book");

        im.getPersistentDataContainer()
            .set(getExpKey(), PersistentDataType.INTEGER, saveLevel);

        setItemMeta(im);

        var lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "This book contains saved levels.");
        lore.add("Amount: " + saveLevel);
        lore.add("Click to use.");
        im.setLore(lore);
    }

    public static int getSavedExp(ItemStack i){
        if(!i.hasItemMeta())
            return 0;

        ItemMeta im = i.getItemMeta();

        if(!im.getPersistentDataContainer().has(getExpKey(), PersistentDataType.INTEGER))
            return 0;

        return im.getPersistentDataContainer().get(getExpKey(), PersistentDataType.INTEGER);
    }
}
