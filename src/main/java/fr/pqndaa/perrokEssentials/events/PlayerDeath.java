package fr.pqndaa.perrokEssentials.events;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class PlayerDeath implements Listener {

    private final Constants constants;

    private final JavaPlugin plugin;

    public PlayerDeath(Constants constants, JavaPlugin plugin) {
        this.constants = constants;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        try {
            FileConfiguration playerConfig = this.constants.getPlayerConfig(p);

            playerConfig.set("playerDeath", playerConfig.getInt("playerDeath") + 1);
            playerConfig.save(this.constants.playerFile(p,plugin));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
