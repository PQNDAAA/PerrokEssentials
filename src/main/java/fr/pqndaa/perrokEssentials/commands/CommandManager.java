package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    private final JavaPlugin plugin;

    private final Constants constants;

    public CommandManager(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
        Bukkit.getLogger().info("Registering Commands...");
        RegisterCommands();
    }

    private void RegisterCommands() {
        plugin.getCommand("money").setExecutor(new MoneyCommand(plugin,constants));
        plugin.getCommand("pay").setExecutor(new PayCommand(plugin,constants));
        plugin.getCommand("broadcast").setExecutor(new BroadcastCommand(plugin, constants));
        plugin.getCommand("setspawn").setExecutor(new SetSpawnCommand(plugin,constants));
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(plugin,constants));
        plugin.getCommand("reloadpl").setExecutor(new ReloadPluginCommand(plugin,constants));
        Bukkit.getLogger().info("Commands Registered!");
    }
}
