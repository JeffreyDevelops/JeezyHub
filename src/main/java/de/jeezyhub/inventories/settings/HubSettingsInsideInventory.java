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

    List<String> globalChatList = new ArrayList<>();

    List<String> friendsSoundList = new ArrayList<>();

    List<String> privateMsgSoundList = new ArrayList<>();

    public void run(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getInventory().getTitle().contains("§9Settings")) {
            switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§9Friends Requests":
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    friendsItemOnClick(e);
                    break;
                case "§9Friends Sound":
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    friendsSoundItemOnClick(e);
                    break;
                case "§9Private Messages":
                    Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("tpm");
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    privateMessageOnClick(e);
                    break;
                case "§9PM Sound":
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    privateMessageSoundOnClick(e);
                    break;
                case "§9Global Chat":
                    settingsSQL.getSettingsData(e.getWhoClicked().getUniqueId());
                    globalChatOnClick(e);
                    break;
            }
        }
        e.setCancelled(true);
    }


    private void createInventory() {
        HubSettingsInsideInventory = Bukkit.createInventory(null, 18, "§9Settings");
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
        ItemStack friends = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsList.add("§fManage your §9friends §frequests.");
        friendsList.add("");
        friendsList.add("§fCurrently:");
        friendsList.add("");
        friendsList.add("");
        friendsList.add("§f§m-------------------------------");
        friendsList.add("§7Click to toggle friends requests.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.friendsRequests) {
                friendsList.remove(3);
                friendsList.add(3, "§9§l♦ §aEnabled");
            } else {
                friendsList.remove(3);
                friendsList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            friendsList.remove(3);
            friendsList.add(3, "§9§l♦ §aEnabled");
        }

        friendsMeta.setDisplayName("§9Friends Requests");
        friendsMeta.setLore(friendsList);
        friends.setItemMeta(friendsMeta);
        HubSettingsInsideInventory.setItem(1, friends);
    }

    private void friendsItemOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack friends = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsList.add("§fManage your §9friends §frequests.");
        friendsList.add("");
        friendsList.add("§fCurrently:");
        friendsList.add("");
        friendsList.add("");
        friendsList.add("§f§m-------------------------------");
        friendsList.add("§7Click to toggle friends requests.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.friendsRequests) {
                friendsList.remove(3);
                friendsList.add(3, "§9§l♦ §cDisabled");
                friendsSQL.friendsSwitcherMYSQL((Player) e.getWhoClicked(), String.valueOf(false), "§cdisabled");
            } else {
                friendsList.remove(3);
                friendsList.add(3, "§9§l♦ §aEnabled");
                friendsSQL.friendsSwitcherMYSQL((Player) e.getWhoClicked(), String.valueOf(true), "§2enabled");
            }
        } else {
            friendsList.remove(3);
            friendsList.add(3, "§9§l♦ §cDisabled");
            friendsSQL.friendsSwitcherMYSQL((Player) e.getWhoClicked(), String.valueOf(false), "§cdisabled");
        }

        friendsMeta.setDisplayName("§9Friends Requests");
        friendsMeta.setLore(friendsList);
        friends.setItemMeta(friendsMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(1, friends);
    }

    private void friendsSoundItem() {
        ItemStack friends = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsSoundList.add("§fManage your §9friends §fsound.");
        friendsSoundList.add("");
        friendsSoundList.add("§fCurrently:");
        friendsSoundList.add("");
        friendsSoundList.add("");
        friendsSoundList.add("§f§m-------------------------------");
        friendsSoundList.add("§7Click to toggle friends sound.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsFriendsSound) {
                friendsSoundList.remove(3);
                friendsSoundList.add(3, "§9§l♦ §aEnabled");
            } else {
                friendsSoundList.remove(3);
                friendsSoundList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            friendsSoundList.remove(3);
            friendsSoundList.add(3, "§9§l♦ §aEnabled");
        }

        friendsMeta.setDisplayName("§9Friends Sound");
        friendsMeta.setLore(friendsSoundList);
        friends.setItemMeta(friendsMeta);
        HubSettingsInsideInventory.setItem(3, friends);
    }

    private void friendsSoundItemOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack friends = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemMeta friendsMeta = friends.getItemMeta();

        friendsSoundList.add("§fManage your §9friends §fsound.");
        friendsSoundList.add("");
        friendsSoundList.add("§fCurrently:");
        friendsSoundList.add("");
        friendsSoundList.add("");
        friendsSoundList.add("§f§m-------------------------------");
        friendsSoundList.add("§7Click to toggle friends sound.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsFriendsSound) {
                friendsSoundList.remove(3);
                friendsSoundList.add(3, "§9§l♦ §cDisabled");
                settingsSQL.disableFriendsSound((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage("§7You successfully §cdisabled §7friends message §9sounds§7.");
            } else {
                friendsSoundList.remove(3);
                friendsSoundList.add(3, "§9§l♦ §aEnabled");
                settingsSQL.enableFriendsSound((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage("§7You successfully §2enabled §7friends message §9sounds§7.");
            }
        } else {
            friendsSoundList.remove(3);
            friendsSoundList.add(3, "§9§l♦ §cDisabled");
            settingsSQL.disableFriendsSound((Player) e.getWhoClicked());
            e.getWhoClicked().sendMessage("§7You successfully §cdisabled §7friends message §9sounds§7.");
        }

        friendsMeta.setDisplayName("§9Friends Sound");
        friendsMeta.setLore(friendsSoundList);
        friends.setItemMeta(friendsMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(3, friends);
    }


    private void privateMessageItem() {
        ItemStack privateMsg = new ItemStack(Material.BOOK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgList.add("§fManage your §9private §fmessages.");
        privateMsgList.add("");
        privateMsgList.add("§fCurrently:");
        privateMsgList.add("");
        privateMsgList.add("");
        privateMsgList.add("§f§m-------------------------------");
        privateMsgList.add("§7Click to toggle private messages.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsMsg) {
                privateMsgList.remove(3);
                privateMsgList.add(3, "§9§l♦ §aEnabled");
            } else {
                privateMsgList.remove(3);
                privateMsgList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            privateMsgList.remove(3);
            privateMsgList.add(3, "§9§l♦ §aEnabled");
        }

        privateMsgMeta.setDisplayName("§9Private Messages");
        privateMsgMeta.setLore(privateMsgList);
        privateMsg.setItemMeta(privateMsgMeta);
        HubSettingsInsideInventory.setItem(5, privateMsg);
    }


    private void privateMessageOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack privateMsg = new ItemStack(Material.BOOK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgList.add("§fManage your §9private §fmessages.");
        privateMsgList.add("");
        privateMsgList.add("§fCurrently:");
        privateMsgList.add("");
        privateMsgList.add("");
        privateMsgList.add("§f§m-------------------------------");
        privateMsgList.add("§7Click to toggle private messages.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsMsg) {
                privateMsgList.remove(3);
                privateMsgList.add(3, "§9§l♦ §aEnabled");
            } else {
                privateMsgList.remove(3);
                privateMsgList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            privateMsgList.remove(3);
            privateMsgList.add(3, "§9§l♦ §aEnabled");
        }

        privateMsgMeta.setDisplayName("§9Private Messages");
        privateMsgMeta.setLore(privateMsgList);
        privateMsg.setItemMeta(privateMsgMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(5, privateMsg);
    }


    private void privateMessageSound() {
        ItemStack privateMsg = new ItemStack(Material.MELON_BLOCK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgSoundList.add("§fManage your §9private §fmessages sound.");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("§fCurrently:");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("§f§m-------------------------------");
        privateMsgSoundList.add("§7Click to toggle private message sound.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsPmSound) {
                privateMsgSoundList.remove(3);
                privateMsgSoundList.add(3, "§9§l♦ §aEnabled");
            } else {
                privateMsgSoundList.remove(3);
                privateMsgSoundList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            privateMsgSoundList.remove(3);
            privateMsgSoundList.add(3, "§9§l♦ §aEnabled");
        }

        privateMsgMeta.setDisplayName("§9PM Sound");
        privateMsgMeta.setLore(privateMsgSoundList);
        privateMsg.setItemMeta(privateMsgMeta);
        HubSettingsInsideInventory.setItem(7, privateMsg);
    }

    private void privateMessageSoundOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack privateMsg = new ItemStack(Material.MELON_BLOCK, 1);
        ItemMeta privateMsgMeta = privateMsg.getItemMeta();

        privateMsgSoundList.add("§fManage your §9private §fmessages sound.");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("§fCurrently:");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("");
        privateMsgSoundList.add("§f§m-------------------------------");
        privateMsgSoundList.add("§7Click to toggle private message sound.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsPmSound) {
                privateMsgSoundList.remove(3);
                privateMsgSoundList.add(3, "§9§l♦ §cDisabled");
                settingsSQL.disablePmSound((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage("§7You successfully §cdisabled §7private message §9sounds§7.");
            } else {
                privateMsgSoundList.remove(3);
                privateMsgSoundList.add(3, "§9§l♦ §aEnabled");
                settingsSQL.enablePmSound((Player) e.getWhoClicked());
                e.getWhoClicked().sendMessage("§7You successfully §2enabled §7private message §9sounds§7.");
            }
        } else {
            privateMsgSoundList.remove(3);
            privateMsgSoundList.add(3, "§9§l♦ §cDisabled");
            settingsSQL.disablePmSound((Player) e.getWhoClicked());
            e.getWhoClicked().sendMessage("§7You successfully §cdisabled §7private message §9sounds§7.");
        }


        privateMsgMeta.setDisplayName("§9PM Sound");
        privateMsgMeta.setLore(privateMsgSoundList);
        privateMsg.setItemMeta(privateMsgMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(7, privateMsg);
    }


    private void globalChatItem() {
        ItemStack globalChat = new ItemStack(Material.SIGN, 1);
        ItemMeta globalChatItemMeta = globalChat.getItemMeta();

        globalChatList.add("§fManage your §9global §fchat.");
        globalChatList.add("");
        globalChatList.add("§fCurrently:");
        globalChatList.add("");
        globalChatList.add("");
        globalChatList.add("§f§m-------------------------------");
        globalChatList.add("§7Click to toggle global chat.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsGlobalChat) {
                globalChatList.remove(3);
                globalChatList.add(3, "§9§l♦ §aEnabled");
            } else {
                globalChatList.remove(3);
                globalChatList.add(3, "§9§l♦ §cDisabled");
            }
        } else {
            globalChatList.remove(3);
            globalChatList.add(3, "§9§l♦ §aEnabled");
        }

        globalChatItemMeta.setDisplayName("§9Global Chat");
        globalChatItemMeta.setLore(globalChatList);
        globalChat.setItemMeta(globalChatItemMeta);
        HubSettingsInsideInventory.setItem(13, globalChat);
    }


    private void globalChatOnClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        ItemStack globalChat = new ItemStack(Material.SIGN, 1);
        ItemMeta globalChatItemMeta = globalChat.getItemMeta();

        globalChatList.add("§fManage your §9global §fchat.");
        globalChatList.add("");
        globalChatList.add("§fCurrently:");
        globalChatList.add("");
        globalChatList.add("");
        globalChatList.add("§f§m-------------------------------");
        globalChatList.add("§7Click to toggle global chat.");

        if (settingsSQL.playerUUID != null) {
            if (settingsSQL.settingsGlobalChat) {
                globalChatList.remove(3);
                globalChatList.add(3, "§9§l♦ §cDisabled");
                settingsSQL.disableGlobalChat((Player) e.getWhoClicked());
            } else {
                globalChatList.remove(3);
                globalChatList.add(3, "§9§l♦ §aEnabled");
                settingsSQL.enableGlobalChat((Player) e.getWhoClicked());
            }
        } else {
            globalChatList.remove(3);
            globalChatList.add(3, "§9§l♦ §cDisabled");
            settingsSQL.disableGlobalChat((Player) e.getWhoClicked());
        }

        globalChatItemMeta.setDisplayName("§9Global Chat");
        globalChatItemMeta.setLore(globalChatList);
        globalChat.setItemMeta(globalChatItemMeta);
        settingsMap.get((Player) e.getWhoClicked()).setItem(13, globalChat);
    }



    private void setInnerItems(org.bukkit.event.player.PlayerInteractEvent e) {
        settingsSQL.getSettingsData(e.getPlayer().getUniqueId());
        friendsItem();
        friendsSoundItem();
        privateMessageItem();
        privateMessageSound();
        globalChatItem();
    }


    public void openInv(org.bukkit.event.player.PlayerInteractEvent e) {
        createInventory();
        setPlaceHolders();
        setInnerItems(e);
        settingsMap.put(e.getPlayer(), HubSettingsInsideInventory);
        e.getPlayer().openInventory(settingsMap.get(e.getPlayer()));
        privateMsgList.clear();
        privateMsgSoundList.clear();
        friendsList.clear();
        friendsSoundList.clear();
        globalChatList.clear();
    }
}