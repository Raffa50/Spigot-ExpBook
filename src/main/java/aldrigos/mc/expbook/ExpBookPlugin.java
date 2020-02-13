package aldrigos.mc.expbook;

import aldrigos.mc.expbook.commands.*;
import aldrigos.mc.expbook.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ExpBookPlugin extends JavaPlugin {
    static final String dir = "plugins/expbook/";
    static ExpBookPlugin instance;

    private Config conf;

    public ExpBookPlugin(){
        instance = this;
    }

    @Override
    public void onEnable(){
        saveDefaultConfig();
        conf = new Config(getConfig());

        new SaveXpCommand(conf).register();
        getServer().getPluginManager().registerEvents(new UsageListener(), this);
    }

    @Override
    public void onDisable(){
        saveConfig();
    }
}
