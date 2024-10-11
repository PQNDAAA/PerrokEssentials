package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {

    private JavaPlugin plugin;

    private Constants constants;

    public SpawnCommand(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.constants.getNoPlayerMessage());
            return true;
        }

        Player p = (Player) sender;

        if (args.length != 0) {
            p.sendMessage(this.constants.getErrorPrefix() + " /spawn");
            return true;
        } else {
            Location spawnLocation = plugin.getConfig().getLocation("server-spawn");

            if(spawnLocation == null) {
                p.sendMessage(this.constants.getErrorPrefix() + " Le spawn n'est pas encore défini.");
                return true;
            }

            new BukkitRunnable() {
                int timer = 3;

                @Override
                public void run() {
                    if (timer > 0) {
                        p.sendMessage(ChatColor.GREEN + "➤ Vous allez être téléporté au spawn dans " + timer);
                        timer--;
                    } else {
                        p.teleport(spawnLocation);
                        p.sendMessage(ChatColor.GREEN + "➤ Vous avez bien été teleporté au spawn du serveur.");
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 20, 20);
            return true;
        }
    }
}
