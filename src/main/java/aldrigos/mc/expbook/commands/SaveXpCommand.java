package aldrigos.mc.expbook.commands;

import aldrigos.mc.expbook.*;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SaveXpCommand extends CommandBase {
    private final Config conf;

    public SaveXpCommand(Config conf){
        super("savexp",
                "Save experience into a book",
                "/savexp [L] L = level to save. example: /savexp 2",
                "expbook.save");

        this.conf = conf;
    }

    @Override
    public boolean execute(CommandSender sender, String name, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("[ExpBook]This command is a player only command");
            return false;
        }

//        if(!sender.hasPermission("expbook.create")){
//            sender.sendMessage(ChatColor.RED+"[ExpBook]you don't have permission"+ChatColor.RESET);
//            return false;
//        }

        var player = (Player) sender;
        int saveLevel = 1;

        if(args.length > 0)
            try {
                saveLevel = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex){}

        if(saveLevel <= 0){
            sender.sendMessage(ChatColor.RED+"[ExpBook]saveLevel must be >0"+ChatColor.RESET);
            return false;
        }

        if(conf.maxExp4Book > 0 && saveLevel > conf.maxExp4Book){
            sender.sendMessage(ChatColor.RED+"[ExpBook]saveLevel must be <="+conf.maxExp4Book+ChatColor.RESET);
            return false;
        }

        if(player.getGameMode() != GameMode.CREATIVE && player.getLevel() < saveLevel){
            player.sendMessage(ChatColor.RED+"[ExpBook]your level is not sufficient"+ChatColor.RESET);
            return false;
        }

        if(player.getGameMode() != GameMode.CREATIVE) {
            if(conf.requireBook){
                if(!player.getInventory().contains(Material.BOOK)){
                    player.sendMessage(ChatColor.RED+"[ExpBook]You need a book"+ChatColor.RESET);
                    return false;
                }

                player.getInventory().remove(new ItemStack(Material.BOOK, 1));
            }

            player.setLevel(player.getLevel() - saveLevel);
        }

        player.getInventory().addItem(new ExpBookItem(saveLevel));

        return false;
    }
}
