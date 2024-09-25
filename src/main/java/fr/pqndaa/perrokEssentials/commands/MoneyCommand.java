package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MoneyCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    private final Constants constants;

    public MoneyCommand(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + this.constants.getNoPlayerMessage());
            return true;
        }
        Player p = (Player) commandSender;
        if (strings.length < 1) {
            p.sendMessage(ChatColor.RED + p.getName() + ": " + this.constants.getMoneyAmount(p) + "$");
            return true;
        } else if (strings.length > 1) {
            p.sendMessage(this.constants.getErrorPrefix() + " /money <joueur>.");
            return true;
        } else {
            String targetPlayerString = strings[0];
            Player targetPlayer = plugin.getServer().getPlayer(targetPlayerString);

            if (targetPlayer == null) {
                OfflinePlayer offlinePlayer = plugin.getServer().getOfflinePlayer(targetPlayerString);
                if (offlinePlayer.hasPlayedBefore()) {
                    p.sendMessage(ChatColor.RED + offlinePlayer.getName() + ": " + this.constants.getMoneyAmount(offlinePlayer.getPlayer()) + "$");
                    return true;
                } else {
                    p.sendMessage(this.constants.getErrorPrefix() + " " +
                            this.constants.getNoPlayerExists().replace("%player%", targetPlayerString));
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.RED + targetPlayer.getName() + ": " + this.constants.getMoneyAmount(targetPlayer) + "$");
                return true;
            }
        }
    }
}
