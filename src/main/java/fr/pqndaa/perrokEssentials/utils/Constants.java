package fr.pqndaa.perrokEssentials.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Constants {

    private final JavaPlugin plugin;

    private final String broadcastPrefix = ChatColor.RED + "[Broadcast]";

    private final String moneyPrefix = ChatColor.RED + "Money:";

    private final String errorPrefix = ChatColor.RED + "Erreur:";

    private final String noPlayerExists = ChatColor.RED + "Le joueur %player% n'existe pas.";

    public Constants(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getNoPlayerExists(){
        return noPlayerExists;
    }

    public String getErrorPrefix() {
        return errorPrefix;
    }

    public String getMoneyPrefix(){
        return moneyPrefix;
    }

    public String getBroadcastPrefix() {
        return broadcastPrefix;
    }

    public String getQuitMessage(){ return plugin.getConfig().getString("quit-message"); }

    public String getJoinMessage(){ return plugin.getConfig().getString("join-message"); }

    public String getNoOpMessage() {
        return plugin.getConfig().getString("noOp-message");
    }

    public String getNoPlayerMessage() {
        return plugin.getConfig().getString("noPlayer-message");
    }

    public String getWelcomeMessage() {
        return plugin.getConfig().getString("welcome-message");
    }

    public String getNewsMessage() {
        return plugin.getConfig().getString("news-message");
    }

    public Integer getStartMoneyAmount(){
        return plugin.getConfig().getInt("start-money-amount");
    }

    public void reduceMoneyAmount(Player player, Integer amount) {
        File playerFile = playerFile(player,plugin);

        if(playerFile.exists()){
            try{
                FileConfiguration playerConfig = getPlayerConfig(player);
                playerConfig.set("playerMoney", playerConfig.getInt("playerMoney") - amount);
                playerConfig.save(playerFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addMoneyAmount(Player player, Integer moneyAmount) {
        File playerFile = playerFile(player,plugin);

        if (playerFile.exists()) {
            try {
                FileConfiguration playerConfig = getPlayerConfig(player);
                playerConfig.set("playerMoney", playerConfig.getInt("playerMoney") + moneyAmount);
                playerConfig.save(playerFile);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public FileConfiguration getPlayerConfig(Player player) {
        FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile(player,plugin));
        return playerConfig;
    }

    public File playerFile(Player player, JavaPlugin plugin) {
        File playerFile = new File(plugin.getDataFolder() + "/playerdata", getPlayerUUID(player)+".dat");
        return playerFile;
    }

    public String getPlayerUUID(Player player){
        return player.getUniqueId().toString();
    }

    public Integer getMoneyAmount(Player player) {
        FileConfiguration playerConfig = getPlayerConfig(player);
        return playerConfig.getInt("playerMoney");
    }

    public Integer getPlayerDeath(Player player) {
        FileConfiguration playerConfig = getPlayerConfig(player);
        return playerConfig.getInt("playerDeath");
    }

}
