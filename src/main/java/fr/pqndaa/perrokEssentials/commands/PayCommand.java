package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PayCommand implements CommandExecutor {


    private final JavaPlugin plugin;

    private final Constants constants;

    public PayCommand(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.constants.getNoPlayerMessage());
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(this.constants.getErrorPrefix() + " /pay <player> <amount>.");
            return true;
        }

        Player p = (Player) sender;
        String targetPlayerString = args[0];
        int amount = Integer.parseInt(args[1]);
        Player targetPlayer = plugin.getServer().getPlayer(targetPlayerString);

        if (args.length == 2) {
           if(targetPlayerString.equalsIgnoreCase(p.getName())) {
               p.sendMessage(this.constants.getErrorPrefix() + " /pay <player> <amount>.");
               return true;
           } else {
               this.constants.addMoneyAmount(targetPlayer, amount);
               this.constants.reduceMoneyAmount(p, amount);
               p.sendMessage("➤ Vous avez donné "+ amount + "$ à " + targetPlayer.getName() + ".");
               return true;
           }
        }
        return true;
    }
}
