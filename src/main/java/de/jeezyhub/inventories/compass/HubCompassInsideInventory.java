package de.jeezyhub.inventories.compass;

import de.jeezyhub.queue.QueueManager;
import de.jeezyhub.utils.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static de.jeezyhub.utils.ArrayStorage.perPlayerInventory;
import static de.jeezyhub.utils.ArrayStorage.perPlayerInventoryOpened;
import static de.jeezyhub.utils.BungeeChannelApi.selectedPlayerCount;

public class HubCompassInsideInventory {

    Inventory hubInsideInventory;

    ItemStack practice = new ItemStack(Material.DIAMOND_SWORD, 1);

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    QueueManager queueManager = new QueueManager();

    public void run(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("§9§lGame§f§lmodes")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lPractice")) {
                queueManager.add(e);
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

    private void setActualItem(org.bukkit.event.player.PlayerInteractEvent e) {
        ItemMeta practiceMeta = practice.getItemMeta();
        practiceMeta.setDisplayName("§9§lPractice");
        List<String> desc = new ArrayList<>();
        desc.add("§7PvPBots, 1v1s, 2v2s");
        desc.add("§7Duels, Events & Parties");
        desc.add("                          ");
        bungeeChannelApi.getSelectedPlayerCount();
        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                desc.add("§fPlayers: §9§l" + selectedPlayerCount);
                desc.add("                          ");
                desc.add("§fClick to §9join§7!");
                practiceMeta.setLore(desc);
                practice.setItemMeta(practiceMeta);
                perPlayerInventory.get(e.getPlayer().getUniqueId()).setItem(4, practice);
            }
        }.start();
    }

    public void openInv(org.bukkit.event.player.PlayerInteractEvent e) {
        createInventory();
        setPlaceHolders();
        setActualItem(e);
        perPlayerInventory.put(e.getPlayer().getUniqueId(), hubInsideInventory);
        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                e.getPlayer().openInventory(perPlayerInventory.get(e.getPlayer().getUniqueId()));
                System.out.println(perPlayerInventoryOpened.get(e.getPlayer().getUniqueId()));
                if (perPlayerInventoryOpened.containsKey(e.getPlayer().getUniqueId())) {
                    if (perPlayerInventoryOpened.get(e.getPlayer().getUniqueId())) {
                        System.out.println("RETURNED HERE");
                        return;
                    }
                }
                perPlayerInventoryOpened.put(e.getPlayer().getUniqueId(), true);
                scheduleHubItems(e);

            }
        }.start();

    }

    public void scheduleHubItems(org.bukkit.event.player.PlayerInteractEvent e) {

        Timer time = new Timer();
        TimerTask timeSchedule = new TimerTask() {
            @Override
            public void run() {
                if (!perPlayerInventory.containsKey(e.getPlayer().getUniqueId())) {
                    System.out.println("STOPPED");
                    time.purge();
                    time.cancel();
                    return;
                }
                setActualItem(e);
                System.out.println("working");
            }
        };
        time.scheduleAtFixedRate(timeSchedule, 2000, 2000);
    }
}