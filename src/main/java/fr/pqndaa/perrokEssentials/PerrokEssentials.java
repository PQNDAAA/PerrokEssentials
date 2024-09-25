package fr.pqndaa.perrokEssentials;

import fr.pqndaa.perrokEssentials.commands.CommandManager;
import fr.pqndaa.perrokEssentials.events.EventManager;
import fr.pqndaa.perrokEssentials.utils.Constants;
import fr.pqndaa.perrokEssentials.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PerrokEssentials extends JavaPlugin {

    private Constants constants;
    private ItemUtils itemUtils;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        constants = new Constants(this);
        itemUtils = new ItemUtils();

        new EventManager(this,constants);
        new CommandManager(this, constants);

        createPlayersFolder();

        Bukkit.getLogger().info("Plugin PerrokEssentials enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Plugin PerrokEssentials disabled");
    }

    public void createPlayersFolder(){
        File playersFolder = new File(getDataFolder(), "playerdata");

        if(!playersFolder.exists()){
            playersFolder.mkdir();
        }
    }

    public Constants getConstants() {
        return constants;
    }
    public ItemUtils getItemUtils() {return itemUtils;}
}
