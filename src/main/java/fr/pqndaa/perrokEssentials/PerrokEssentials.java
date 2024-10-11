package fr.pqndaa.perrokEssentials;

import fr.pqndaa.perrokEssentials.commands.CommandManager;
import fr.pqndaa.perrokEssentials.events.EventManager;
import fr.pqndaa.perrokEssentials.utils.CharRepo;
import fr.pqndaa.perrokEssentials.utils.Constants;
import fr.pqndaa.perrokEssentials.utils.ItemUtils;
import fr.pqndaa.perrokEssentials.utils.UtilManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PerrokEssentials extends JavaPlugin {

    private UtilManager utilManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        utilManager = new UtilManager(this);
        new EventManager(this,utilManager.getConstants());
        new CommandManager(this, utilManager.getConstants());

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

    public UtilManager getUtilManager() {return utilManager;}
}
