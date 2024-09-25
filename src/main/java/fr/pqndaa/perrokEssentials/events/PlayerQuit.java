package fr.pqndaa.perrokEssentials.events;

import fr.pqndaa.perrokEssentials.utils.Constants;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerQuit implements Listener {

    private final Constants constants;

    public PlayerQuit(Constants constants) {
        this.constants = constants;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.YELLOW + this.constants.getQuitMessage().replace("%player%", p.getName()));
    }



}
