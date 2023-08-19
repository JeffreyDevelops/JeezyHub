package de.jeezyhub.inventories.settings;

import de.jeezycore.db.FriendsSQL;
import de.jeezycore.db.SettingsSQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import static de.jeezyhub.utils.ArrayStorage.settingsMap;

public class HubSettingsInsideInventory {

    Inventory HubSettingsInsideInventory;

    SettingsSQL settingsSQL = new SettingsSQL();

    FriendsSQL friendsSQL = new FriendsSQL();

    List<String> privateMsgList = new ArrayList<>();

    List<String> friendsList = new ArrayList<>();

    public void run(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("§9Settings")) {
            switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§9Friends requests":
                    friendsSQL.getFriendsStatus((Player) e.getWhoClicked());
                    friendsItemOnClick(e);
                    break;
                case "§9Private messages":
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("tpm");
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    privateMessageOnClick(e);
                    break;
            }
        }
        e.setCancelled(true);
    }


    private void createInventory() {
        HubSettingsInsideInventory = Bukkit.createInventory(null, 9, "§9Settings");
    }


    private void setPlaceHolders() {
        for (int i = 0; i < HubSettingsInsideInventory.getSize(); i++) {
            ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ((int) 11));
            ItemMeta placerHolderMeta = placeholder.getItemMeta();
            placerHolderMeta.setDisplayName("§9");
            List<String> desc = new ArrayList<>();
            desc.add("");
            placerHolderMeta.setLore(desc);
            placeholder.setItemMeta(placerHolderMeta);
            HubSettingsInsideInventory.setItem(i, placeholder);
        }
    }

    private void friendsItem() {
        ItemStack friends = new ItemStack(Material.SKULL_ITEM, 1);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsList.add("§a§aEnabled");
        friendsList.add("§cDisabled");

        System.out.println(friendsSQL.friendsStatus);

        if (friendsSQL.playerUUID != null) {
            if (friendsSQL.friendsStatus) {
                friendsList.remove(0);
                friendsList.add(0, "§9§l♦ §aEnabled");
            } else {
                friendsList.remove(1);
                friendsList.add(1, "§9§l♦ §cDisabled");
            }
        } else {
            friendsList.remove(0);
            friendsList.add(0, "§9§l♦ §aEnabled");
        }

        friendsMeta.setDisplayName("§9Friends requests");
        friendsMeta.setLore(friendsList);
        friends.setItemMeta(friendsMeta);
        HubSettingsInsideInventory.setItem(3, friends);
    }

    private void privateMessageOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack privateMsg = new ItemStack(Material.BOOK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgList.add("§aEnabled");
        privateMsgList.add("§cDisabled");

        if (settingsSQL.settingsMsg) {
            privateMsgList.remove(1);
            privateMsgList.add(1, "§9§l♦ §cDisabled");
        } else {
            privateMsgList.remove(0);
            privateMsgList.add(0, "§9§l♦ §aEnabled");
        }

        privateMsgMeta.setDisplayName("§9Private messages");
        privateMsgMeta.setLore(privateMsgList);
        privateMsg.setItemMeta(privateMsgMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(5, privateMsg);
    }

    private void friendsItemOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack friends = new ItemStack(Material.SKULL_ITEM, 1);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsList.add("§a§aEnabled");
        friendsList.add("§cDisabled");

            if (friendsSQL.friendsStatus) {
                friendsList.remove(0);
                friendsList.add(0, "§9§l♦ §aEnabled");
                friendsSQL.friendsSwitcherMYSQL((Player) e.getWhoClicked(), String.valueOf(false), "§aenabled");
            } else {
                friendsList.remove(1);
                friendsList.add(1, "§9§l♦ §cDisabled");
                friendsSQL.friendsSwitcherMYSQL((Player) e.getWhoClicked(), String.valueOf(true), "§cdisabled");
            }

        friendsMeta.setDisplayName("§9Friends requests");
        friendsMeta.setLore(friendsList);
        friends.setItemMeta(friendsMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(3, friends);
    }

    private void privateMessageItem() {
        ItemStack privateMsg = new ItemStack(Material.BOOK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgList.add("§aEnabled");
        privateMsgList.add("§cDisabled");

        if (settingsSQL.settingsMsg) {
            privateMsgList.remove(1);
            privateMsgList.add(1, "§9§l♦ §cDisabled");
        } else {
            privateMsgList.remove(0);
            privateMsgList.add(0, "§9§l♦ §aEnabled");
        }

        privateMsgMeta.setDisplayName("§9Private messages");
        privateMsgMeta.setLore(privateMsgList);
        privateMsg.setItemMeta(privateMsgMeta);
        HubSettingsInsideInventory.setItem(5, privateMsg);
    }


    private void setInnerItems(org.bukkit.event.player.PlayerInteractEvent e) {
        settingsSQL.getSettingsData(e.getPlayer().getUniqueId());
        friendsSQL.getFriendsStatus(e.getPlayer());
        friendsItem();
        privateMessageItem();
    }


    public void openInv(org.bukkit.event.player.PlayerInteractEvent e) {
        createInventory();
        setPlaceHolders();
        setInnerItems(e);
        settingsMap.put(e.getPlayer(), HubSettingsInsideInventory);
        e.getPlayer().openInventory(settingsMap.get(e.getPlayer()));
        privateMsgList.clear();
        friendsList.clear();
    }
}