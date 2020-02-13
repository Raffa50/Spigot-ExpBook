package aldrigos.mc.expbook;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private final FileConfiguration fc;
    public final boolean requireBook;
    public final int maxExp4Book;

    public Config(FileConfiguration fc){
        this.fc = fc;
        //fc.addDefault("requireBook", false);
        //fc.addDefault("maxExp4Book", 0);

        requireBook = fc.getBoolean("requireBook");
        maxExp4Book = fc.getInt("maxExp4Book");
    }
}
