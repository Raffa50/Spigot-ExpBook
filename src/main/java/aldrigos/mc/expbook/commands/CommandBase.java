package aldrigos.mc.expbook.commands;

import aldrigos.mc.expbook.exceptions.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandBase extends Command {
    public final String name;

    protected CommandBase(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);

        this.name = name;
    }

    protected CommandBase(String name, String description, String usageMessage, String permission){
        this(name, description, usageMessage, new ArrayList<>());
    }

    public CommandBase withPermission(String permission){
        setPermission(permission);
        return this;
    }

    public CommandBase withPermissionMsg(String permissionMsg) {
        setPermissionMessage(permissionMsg);
        return this;
    }

    public void register(){
        try {
            final var bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);

            var cmdMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            cmdMap.register(this.name, this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new NotSupportedException();
        }
    }
}
