package fr.pqndaa.perrokEssentials.events;

import fr.pqndaa.perrokEssentials.utils.Constants;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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

public class PlayerJoin implements Listener {

    private final JavaPlugin plugin;

    private final Constants constants;

    private final String resourcePackUrl = "https://github.com/PQNDAAA/PackServer/releases/download/v1.0.0/PackServer.zip";

    public PlayerJoin(JavaPlugin plugin, Constants constants) {
        this.plugin = plugin;
        this.constants = constants;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        String playerUUID = p.getUniqueId().toString();
        File playerFile = new File(plugin.getDataFolder()+"/playerdata", playerUUID + ".dat");
        p.setResourcePack(resourcePackUrl);

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
            TextComponent mainMessage = new TextComponent(ChatColor.RED + this.constants.getNewsMessage());
            TextComponent documentationLink = new TextComponent(ChatColor.RED + "" + ChatColor.BOLD + "ici");
            documentationLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://github.com/PQNDAAA/PerrokEssentials"));
            documentationLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text("Cliquez-ici pour obtenir les informations")));
            mainMessage.addExtra(documentationLink);
            p.spigot().sendMessage(mainMessage);
        }
    }
}
