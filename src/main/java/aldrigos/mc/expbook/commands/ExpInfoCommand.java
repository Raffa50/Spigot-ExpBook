package aldrigos.mc.expbook.commands;

import aldrigos.mc.expbook.ExpBook;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ExpInfoCommand extends CommandBase {
    public ExpInfoCommand(){
        super("expinfo", "Get your total xp", "/expinfo", "expbook.expinfo");
    }

    @Override
    public boolean execute(CommandSender sender, String name, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("[ExpBook]This command is a player only command");
            return false;
        }

        var player = (Player) sender;
        player.sendMessage(ChatColor.YELLOW+"[ExpBook]Total exp: "+ ExpBook.getTotalExperience(player)+ChatColor.RESET);

        return true;
    }
}
