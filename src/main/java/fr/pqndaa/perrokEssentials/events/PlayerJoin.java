package fr.pqndaa.perrokEssentials.events;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerJoin implements Listener {

    private final JavaPlugin plugin;

    private final Constants constants;

    public PlayerJoin(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String playerUUID = p.getUniqueId().toString();
        File playerFile = new File(plugin.getDataFolder()+"/playerdata", playerUUID + ".dat");

        event.setJoinMessage(ChatColor.YELLOW + this.constants.getJoinMessage().replace("%player%",p.getName()));

        if(!playerFile.exists()) {
            try {
                if(playerFile.createNewFile()) {
                    FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
                    playerConfig.set("playerUUID", playerUUID);
                    playerConfig.set("playerName", p.getName());
                    playerConfig.set("playerMoney",this.constants.getStartMoneyAmount());
                    playerConfig.set("playerDeath",0);
                    playerConfig.save(playerFile);

                    p.sendMessage(ChatColor.YELLOW + this.constants.getWelcomeMessage().replace("%player%", p.getName()) + "\n"
                            + ChatColor.RED + this.constants.getNewsMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            p.sendMessage(ChatColor.RED + this.constants.getNewsMessage());
        }
    }
}
