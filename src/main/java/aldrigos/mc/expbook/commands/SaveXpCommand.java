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
                "/savexp <L> .L = exp to save. example: /savexp 1000",
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
        int saveExp = 1;

        if(args.length < 1){
            player.sendMessage(ChatColor.RED+"[ExpBook]saveExp must be >0"+ChatColor.RESET);
            return false;
        }
        try {
            saveExp = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex){}

        if(saveExp <= 0){
            sender.sendMessage(ChatColor.RED+"[ExpBook]saveExp must be >0"+ChatColor.RESET);
            return false;
        }

        if(conf.maxExp4Book > 0 && saveExp > conf.maxExp4Book){
            sender.sendMessage(ChatColor.RED+"[ExpBook]saveExp must be <="+conf.maxExp4Book+ChatColor.RESET);
            return false;
        }

        if(player.getGameMode() != GameMode.CREATIVE && ExpBook.getTotalExperience(player) < saveExp){
            player.sendMessage(ChatColor.RED+"[ExpBook]your exp is not sufficient"+ChatColor.RESET);
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

            player.giveExp(-saveExp);
        }

        player.getInventory().addItem(ExpBook.create(saveExp));

        return true;
    }
}
