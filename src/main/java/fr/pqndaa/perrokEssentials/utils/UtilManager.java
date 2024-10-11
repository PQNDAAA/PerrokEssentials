package fr.pqndaa.perrokEssentials.utils;

import org.bukkit.plugin.java.JavaPlugin;

public class UtilManager {

    private Constants constants;
    private ItemUtils itemUtils;
    private CharRepo charRepo;

    private final JavaPlugin plugin;

    public UtilManager(JavaPlugin plugin) {
        this.plugin = plugin;
        RegisterUtils();
    }

    private void RegisterUtils(){
        constants = new Constants(plugin);
        itemUtils = new ItemUtils();
        charRepo = new CharRepo();
    }

    public Constants getConstants() {
        return constants;
    }
    public ItemUtils getItemUtils() {return itemUtils;}
    public CharRepo getCharRepo() {return charRepo;}
}
