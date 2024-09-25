package fr.pqndaa.perrokEssentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {


    public ItemStack createItem(ItemStack item, String displayName, List<String> loreList,String tagName,String tagValue){
        ItemStack targetItem = item;
        ItemMeta meta = targetItem.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + displayName);
        List<String> lore = createLore(new ArrayList<>(loreList));
        meta.setLore(lore);
        targetItem.setItemMeta(meta);
        addCustomTag(targetItem,tagName,tagValue);
        return targetItem;
    }
    public ItemStack getPlayerSkull(Player player, ItemStack item){
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.setOwningPlayer(player);
        item.setItemMeta(skullMeta);
        return item;
    }

    public void addCustomTag(ItemStack item, String tagName,String value){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            NamespacedKey key = new NamespacedKey("perrokessentials", tagName);
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
            Bukkit.getLogger().info(key.toString() + " " + value + " " + item.getType());
            item.setItemMeta(meta);
        }
    }

    public String getCustomTag(ItemStack item, String tagName){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            NamespacedKey key = new NamespacedKey("perrokessentials", tagName);
            return meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        }
        return null;
    }

    public List<String> createLore(ArrayList<String> lines){
        List<String> lore = new ArrayList<>();
        for(String line : lines){
            lore.add(ChatColor.GRAY + line);
        }
        return lore;
    }

    public void editItemLore(ItemStack item, ArrayList<String> lines) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {

            List<String> currentLore = meta.getLore();

            if (currentLore == null) {
                currentLore = new ArrayList<>();
            }

            for (String line : lines) {
                currentLore.add(line);
            }
            meta.setLore(currentLore);
            item.setItemMeta(meta);
        }
    }

    public ItemMeta clearLore(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setLore(new ArrayList<>());
            return meta;
        }
        return null;
    }

    public void newLoreItem(ItemStack item, ArrayList<String> lines){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            List<String> lore = createLore(lines);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public String getDisplayName(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            return meta.getDisplayName();
        }
        return null;
    }
}
