package aldrigos.mc.expbook;

import aldrigos.mc.expbook.commands.*;
import aldrigos.mc.expbook.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class ExpBookPlugin extends JavaPlugin {
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
        new ExpInfoCommand().register();

        var pm = getServer().getPluginManager();
        pm.registerEvents(new UsageListener(), this);
        if(conf.playerDropsExp)
            pm.registerEvents(new PlayerDeathListener(conf), this);
    }

    @Override
    public void onDisable(){
        saveConfig();
    }
}
