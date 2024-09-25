package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    private final Constants constants;

    public BroadcastCommand(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(this.constants.getNoPlayerMessage());
            return true;
        }
        if (commandSender.isOp()) {
            if (strings.length == 0) {
                commandSender.sendMessage(this.constants.getErrorPrefix() + " /broadcast <message>");
                return true;
            } else {
                StringBuilder messageBuilder = new StringBuilder();

                for (int i = 0; i < strings.length; i++) {
                    messageBuilder.append(strings[i]).append(" ");
                }

                String message = messageBuilder.toString();

                Player targetPlayer = plugin.getServer().getPlayer(commandSender.getName());

                Bukkit.broadcastMessage(this.constants.getBroadcastPrefix() + " " + targetPlayer.getName() + " : " + message);

                return true;
            }
        } else {
            Player p = (Player) commandSender;

            p.sendMessage(ChatColor.RED + this.constants.getNoOpMessage());
            return true;
        }
    }
}
