package de.jeezyhub.inventories.compass.practice;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.jeezycore.main.Main;
import de.jeezyhub.utils.BungeeChannelApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static de.jeezyhub.utils.ArrayStorage.*;

public class HubPracticeInventory {

    Inventory hubPracticeInventory;

    io.github.leonardosnt.bungeechannelapi.BungeeChannelApi api = io.github.leonardosnt.bungeechannelapi.BungeeChannelApi.of(Main.getPlugin(Main.class));


    public void openInv(org.bukkit.event.inventory.InventoryClickEvent e) {

        e.getWhoClicked().closeInventory();
        createInventory();
        setPlaceHolders();
        perPlayerInventory.put(e.getWhoClicked().getUniqueId(), hubPracticeInventory);
        setActualItemsNA(e);
        setActualItemsEU(e);
        setActualItemsSA(e);
        scheduleHubItems(e);

        new Thread() {
            @Override
            public void run() {
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                e.getWhoClicked().openInventory(perPlayerInventory.get(e.getWhoClicked().getUniqueId()));
                System.out.println(perPlayerInventoryOpened.get(e.getWhoClicked().getUniqueId()));
                if (perPlayerInventoryOpened.containsKey(e.getWhoClicked().getUniqueId())) {
                    if (perPlayerInventoryOpened.get(e.getWhoClicked().getUniqueId())) {
                        System.out.println("RETURNED HERE");
                        return;
                    }
                }
                perPlayerInventoryOpened.put(e.getWhoClicked().getUniqueId(), true);

            }
        }.start();
    }


    public void createInventory() {
        hubPracticeInventory = Bukkit.createInventory(null, 27, "§7Server §9§l> §9§lPractice");
    }

    private void setPlaceHolders() {
        for (int i = 0; i < hubPracticeInventory.getSize(); i++) {
            ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ((int) 11));
            ItemMeta placerHolderMeta = placeholder.getItemMeta();
            placerHolderMeta.setDisplayName("§9");
            List<String> desc = new ArrayList<>();
            desc.add("");
            placerHolderMeta.setLore(desc);
            placeholder.setItemMeta(placerHolderMeta);
            hubPracticeInventory.setItem(i, placeholder);
        }
    }

    private void setActualItemsNA(org.bukkit.event.inventory.InventoryClickEvent e) {

        ItemStack practiceNA = new ItemStack(Material.DIAMOND_SWORD, 1);

        ItemMeta practiceNaMeta = practiceNA.getItemMeta();
        practiceNaMeta.setDisplayName("§9§lNA Practice");
        List<String> descNa = new ArrayList<>();


        api.getPlayerCount("practice-NA")
                .whenComplete((oneServerPlayerCount, error) -> {
                        descNa.add("§7Proxy: North America");
                        descNa.add("                          ");

                        descNa.add("§fPlayers: §9§l" + oneServerPlayerCount);
                        descNa.add("                          ");

                        if (!loginServer.get(e.getWhoClicked().getUniqueId()).startsWith("na")) {
                            descNa.add("§cconnect with§7:");
                            descNa.add("§9na.mineral.gg");
                            descNa.add("§cto join!");
                        } else {
                            descNa.add("§fClick to §9join§7!");
                        }
                        practiceNaMeta.setLore(descNa);
                        practiceNA.setItemMeta(practiceNaMeta);
                        perPlayerInventory.get(e.getWhoClicked().getUniqueId()).setItem(13, practiceNA);

                });

    }

    public void setActualItemsEU(org.bukkit.event.inventory.InventoryClickEvent e) {

        ItemStack practiceEU = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta practiceEuMeta = practiceEU.getItemMeta();
        practiceEuMeta.setDisplayName("§9§lEU Practice");
        List<String> descEu = new ArrayList<>();


        api.getPlayerCount("practice-EU")
                .whenComplete((oneServerPlayerCount, error) -> {

        descEu.add("§7Proxy: Europe");
        descEu.add("                          ");

        descEu.add("§fPlayers: §9§l" + oneServerPlayerCount);
        descEu.add("                          ");
        if (!loginServer.get(e.getWhoClicked().getUniqueId()).startsWith("eu")) {
            descEu.add("§cconnect with§7:");
            descEu.add("§9eu.mineral.gg");
            descEu.add("§cto join!");
        } else {
            descEu.add("§fClick to §9join§7!");
        }
        practiceEuMeta.setLore(descEu);
        practiceEU.setItemMeta(practiceEuMeta);
        perPlayerInventory.get(e.getWhoClicked().getUniqueId()).setItem(13, practiceEU);
                });
    }

    public void setActualItemsSA(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack practiceSA = new ItemStack(Material.GOLD_SWORD, 1);
        ItemMeta practiceSaMeta = practiceSA.getItemMeta();
        practiceSaMeta.setDisplayName("§9§lSA Practice");
        List<String> descSa = new ArrayList<>();

        api.getPlayerCount("practice-SA")
                .whenComplete((oneServerPlayerCount, error) -> {

        descSa.add("§7Proxy: South America");
        descSa.add("                          ");


        descSa.add("§fPlayers: §9§l" + oneServerPlayerCount);
        descSa.add("                          ");
        if (!loginServer.get(e.getWhoClicked().getUniqueId()).startsWith("sa")) {
            descSa.add("§cconnect with§7:");
            descSa.add("§9sa.mineral.gg");
            descSa.add("§cto join!");
        } else {
            descSa.add("§fClick to §9join§7!");
        };
        practiceSaMeta.setLore(descSa);
        practiceSA.setItemMeta(practiceSaMeta);
        perPlayerInventory.get(e.getWhoClicked().getUniqueId()).setItem(13, practiceSA);
                });
    }

    public void scheduleHubItems(org.bukkit.event.inventory.InventoryClickEvent e) {

        Timer time = new Timer();
        TimerTask timeSchedule = new TimerTask() {
            @Override
            public void run() {
                if (!perPlayerInventory.containsKey(e.getWhoClicked().getUniqueId())) {
                    System.out.println("STOPPED");
                    time.purge();
                    time.cancel();
                    return;
                }
                setActualItemsNA(e);
                setActualItemsEU(e);
                setActualItemsSA(e);
                System.out.println("working");
            }
        };
        time.scheduleAtFixedRate(timeSchedule, 2000, 2000);
    }

}
