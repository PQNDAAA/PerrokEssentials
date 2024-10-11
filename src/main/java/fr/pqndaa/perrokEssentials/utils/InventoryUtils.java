package fr.pqndaa.perrokEssentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryUtils {

    private Inventory inventory;
    private NamespacedKey inventoryKey;
    private Map<ItemStack, Integer> items;
    private Map<Integer,String> jobsNames;

    public InventoryUtils(Player player, int size, String title, Plugin plugin) {
        inventory = Bukkit.createInventory(player, size, title);
        items = new HashMap<ItemStack, Integer>();
        jobsNames = new HashMap<>();
        inventoryKey = new NamespacedKey(plugin, "custominventory");
    }

    public void createItem(ItemStack item, String displayName,int slot, List<String> loreList, String tagName, String tagValue, int id){
        ItemUtils itemUtils = new ItemUtils();
        ItemStack newItem = itemUtils.createItem(item, displayName, loreList, tagName, tagValue);
        addItem(item,slot,id);
    }

    public void createJobItems(ArrayList<Integer> slots, String name, List<String> lines,int id,String jobName) {
        for (Integer slot : slots) {
            ItemUtils itemUtils = new ItemUtils();
            ItemStack item = itemUtils.createItem(new ItemStack(Material.PAPER), name, lines, "action_type", "open_jobinfo");
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(10);
            item.setItemMeta(meta);
            addItem(item,slot,id);
        }
        jobsNames.put(id, jobName);
    }

    public ItemStack getItem(int itemID){
        for(Map.Entry<ItemStack,Integer> entry : items.entrySet()) {
            ItemStack item = entry.getKey();
            Integer id = entry.getValue();
            if(id.equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    public Integer getItemSlot(ItemStack item) {
        ItemStack[] contents = inventory.getContents();
        for(int i = 0; i < contents.length; i++){
            if(contents[i].equals(item)) {
                return i;
            }
        }
        return 0;
    }

    public void clearInventory(){
        inventory = null;
        items.clear();
        jobsNames.clear();
        inventoryKey = null;
    }

    public void removeItem(ItemStack item){
        items.remove(item);
        inventory.removeItem(item);
    }

    public void addItem(ItemStack item, int slot, int id){
        inventory.setItem(slot, item);
        items.put(item, id);
    }

    public void updateInventoryItem(ItemStack oldItem, ItemStack newItem, int slot, int id){
        removeItem(oldItem);
        addItem(newItem,slot,id);
    }

    public boolean isCustomInventory(Inventory inventory) {return this.inventory.equals(inventory);}

    public Integer getItemId(ItemStack item) {return items.get(item);}

    public NamespacedKey getInventoryKey(){return inventoryKey;}

    public void openInventory(Player player){player.openInventory(inventory);}

    public String getJobName(int id) {return jobsNames.get(id);}

}
