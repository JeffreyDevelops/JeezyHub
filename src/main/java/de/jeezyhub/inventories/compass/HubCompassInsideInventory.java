package de.jeezyhub.inventories.compass;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import com.velocitypowered.api.proxy.server.ServerPing;
import de.jeezyhub.inventories.compass.practice.HubPracticeInventory;
import de.jeezyhub.queue.QueueManager;
import de.jeezyhub.utils.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static de.jeezyhub.utils.ArrayStorage.*;

public class HubCompassInsideInventory {

    Inventory hubInsideInventory;

    ItemStack practice = new ItemStack(Material.DIAMOND_SWORD, 1);

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    QueueManager queueManager = new QueueManager();

    HubPracticeInventory hubPracticeInventory = new HubPracticeInventory();

    public void run(org.bukkit.event.inventory.InventoryClickEvent e) throws UnknownHostException {
        if (e.getInventory().getTitle().contains("§9§lGamemodes")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lPractice")) {
                hubPracticeInventory.openInv(e);
            }

        } else if (e.getInventory().getTitle().contains("§7Server §9§l> §9§lPractice")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lNA Practice")) {
                if (loginServer.get(e.getWhoClicked().getUniqueId()).startsWith("na")) {
                    queueManager.add(e, "practice-NA");
                } else {
                    e.getWhoClicked().sendMessage("§cYou need to join through§7:\n" +
                            "§9na.mineral.gg");
                    e.getWhoClicked().closeInventory();
                }

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lEU Practice")) {
                if (loginServer.get(e.getWhoClicked().getUniqueId()).startsWith("eu")) {
                    queueManager.add(e, "practice-EU");
                } else {
                    e.getWhoClicked().sendMessage("§cYou need to join through§7:\n" +
                            "§9eu.mineral.gg");
                    e.getWhoClicked().closeInventory();
                }

            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lSA Practice")) {
                e.getWhoClicked().sendMessage("§cComing soon!");
                e.getWhoClicked().closeInventory();
            }
        }

        e.setCancelled(true);
    }

    private void createInventory() {
        hubInsideInventory = Bukkit.createInventory(null, 9, "§9§lGamemodes");
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

                desc.add("§fPlayers: §9§l" + bungeeChannelApi.receivedServerCount("practice-NA"));
                desc.add("                          ");
                desc.add("§fClick to §9join§7!");
                practiceMeta.setLore(desc);
                practice.setItemMeta(practiceMeta);
                perPlayerInventory.get(e.getPlayer().getUniqueId()).setItem(4, practice);

    }

    public void openInv(org.bukkit.event.player.PlayerInteractEvent e) {
        createInventory();
        setPlaceHolders();
        perPlayerInventory.put(e.getPlayer().getUniqueId(), hubInsideInventory);
        setActualItem(e);


                e.getPlayer().openInventory(perPlayerInventory.get(e.getPlayer().getUniqueId()));
                System.out.println(perPlayerInventoryOpened.get(e.getPlayer().getUniqueId()));
                if (perPlayerInventoryOpened.containsKey(e.getPlayer().getUniqueId())) {
                    if (perPlayerInventoryOpened.get(e.getPlayer().getUniqueId())) {
                        System.out.println("RETURNED HERE");
                        return;
                    }
                }
                perPlayerInventoryOpened.put(e.getPlayer().getUniqueId(), true);


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