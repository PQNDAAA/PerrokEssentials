package fr.pqndaa.perrokEssentials.events;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EventManager {

    private final JavaPlugin plugin;

    private final Constants constants;

    public EventManager(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
        Bukkit.getLogger().info("Registering Events...");
        RegisterEvents();
    }

    private void RegisterEvents(){
        plugin.getServer().getPluginManager().registerEvents(new PlayerJoin(plugin,constants), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerQuit(constants), plugin);
        plugin.getServer().getPluginManager().registerEvents(new PlayerDeath(constants,plugin), plugin);
        Bukkit.getLogger().info("Events Registered!");
    }
}
