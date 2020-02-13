package aldrigos.mc.expbook;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private final FileConfiguration fc;
    public final boolean requireBook;
    public final int maxExp4Book;
    public final boolean playerDropsExp;
    public final float playerDropExpRate;

    public Config(FileConfiguration fc){
        this.fc = fc;

        requireBook = fc.getBoolean("requireBook");
        maxExp4Book = fc.getInt("maxExp4Book");
        playerDropsExp = fc.getBoolean("playerDropsExp");
        playerDropExpRate = (float)fc.getDouble("playerDropExpRate");
    }
}
