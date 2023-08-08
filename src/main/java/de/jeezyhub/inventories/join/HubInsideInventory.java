package de.jeezyhub.inventories.join;

import de.jeezyhub.utils.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HubInsideInventory {

    Inventory hubInsideInventory;

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();


    public void run(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("§9§lGame§f§lmodes")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lPractice")) {
                bungeeChannelApi.sendToServer((Player) e.getWhoClicked());
                e.getWhoClicked().closeInventory();
                }
            }
        e.setCancelled(true);
        }

    private void createInventory() {
        hubInsideInventory = Bukkit.createInventory(null, 9, "§9§lGame§f§lmodes");
    }

    private void setPlaceHolders() {
        for (int i = 0; i < hubInsideInventory.getSize(); i++) {
            ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ((int) 11));
            ItemMeta placerHolderMeta = placeholder.getItemMeta();
            placerHolderMeta.setDisplayName("§9");
            List<String> desc = new ArrayList<>();
            desc.add("");
            placerHolderMeta.setLore(desc);
            placeholder.setItemMeta(placerHolderMeta);
            hubInsideInventory.setItem(i, placeholder);
        }
    }

    private void setActualItem() {
        ItemStack practice = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta practiceMeta = practice.getItemMeta();
        practiceMeta.setDisplayName("§9§lPractice");
        List<String> desc = new ArrayList<>();
        desc.add("§7PvPBots, 1v1s, 2v2s");
        desc.add("§7Duels, Events & Parties");
        desc.add("                          ");
        desc.add("§fPlayers: §9§l"+0);
        desc.add("                          ");
        desc.add("§fClick to §9join§7!");
        practiceMeta.setLore(desc);
        practice.setItemMeta(practiceMeta);
        hubInsideInventory.setItem(4, practice);
    }

    public void openInv(org.bukkit.event.player.PlayerInteractEvent e) {
        createInventory();
        setPlaceHolders();
        setActualItem();
        e.getPlayer().openInventory(hubInsideInventory);
    }
}