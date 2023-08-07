package de.jeezyhub.inventories.join;

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


        private Inventory playerInventory(PlayerJoinEvent e) {
            e.getPlayer().getInventory().setHeldItemSlot(4);
            return e.getPlayer().getInventory();
        }
}