package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadPluginCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    private final Constants constants;

    public ReloadPluginCommand(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.isOp()) {
            if (args.length == 1) {
                String pluginName = args[0];
                Plugin targetPlugin = Bukkit.getPluginManager().getPlugin(pluginName);

                if (targetPlugin != null) {
                    Bukkit.getPluginManager().disablePlugin(targetPlugin);
                    Bukkit.getPluginManager().enablePlugin(targetPlugin);
                    sender.sendMessage(ChatColor.GREEN + "Le plugin " + targetPlugin + " a bien été redémarré!");
                    return true;
                } else {
                    sender.sendMessage(this.constants.getErrorPrefix() + " Le plugin n'a pas été trouvé.");
                    return true;
                }
            } else {
                sender.sendMessage(this.constants.getErrorPrefix() + " /reloadpl <plugin>");
                return true;
            }
        } else {
            sender.sendMessage(this.constants.getErrorPrefix() + " " + this.constants.getNoOpMessage());
            return true;
        }
    }
}
