package fr.pqndaa.perrokEssentials.commands;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSpawnCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    private final Constants constants;

    public SetSpawnCommand(JavaPlugin plugin, Constants constants) {
        this.constants = constants;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(this.constants.getNoPlayerMessage());
            return true;
        }
        Player p = (Player) sender;
        if(p.isOp()){
            if(args.length != 0){
                p.sendMessage(this.constants.getErrorPrefix() + " /setspawn");
                return true;
            } else {
                plugin.getConfig().set("server-spawn", p.getLocation());
                plugin.saveConfig();

                p.sendMessage(ChatColor.GREEN + "➤ Le spawn a bien été enregistré avec succés en "+ ChatColor.RED + "x" + Math.round(p.getLocation().getX()) + ", y" +
                        Math.round(p.getLocation().getY()) + ", z" + Math.round(p.getLocation().getZ()));
                return true;
            }
        } else {
            p.sendMessage(ChatColor.RED + this.constants.getNoOpMessage());
            return true;
        }
    }
}
