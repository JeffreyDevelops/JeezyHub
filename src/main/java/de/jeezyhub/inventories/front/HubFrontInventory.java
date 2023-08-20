package de.jeezyhub.inventories.front;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubFrontInventory {

        public void setCompassOnJoin(PlayerJoinEvent e) {
            ItemStack selector = new ItemStack(Material.COMPASS, 1);
            ItemMeta selectorMeta = selector.getItemMeta();
            selectorMeta.setDisplayName("§9Gamemodes");
            selector.setItemMeta(selectorMeta);
            playerInventory(e).setItem(4, selector);
        }

        public void setSettingsOnJoin(PlayerJoinEvent e) {
            ItemStack settings = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
            ItemMeta settingsMeta = settings.getItemMeta();
            settingsMeta.setDisplayName("§9Settings");
            settings.setItemMeta(settingsMeta);
            playerInventory(e).setItem(8, settings);
        }

    public void setPlayerVisibilityOnJoin(PlayerJoinEvent e) {
        ItemStack playerVisibility = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        ItemMeta playerVisibilityMeta = playerVisibility.getItemMeta();
        playerVisibilityMeta.setDisplayName("§9Player Visibility §7(§aEnabled§7)");
        playerVisibility.setItemMeta(playerVisibilityMeta);
        playerInventory(e).setItem(0, playerVisibility);
    }

        private Inventory playerInventory(PlayerJoinEvent e) {
            e.getPlayer().getInventory().setHeldItemSlot(4);
            return e.getPlayer().getInventory();
        }
}