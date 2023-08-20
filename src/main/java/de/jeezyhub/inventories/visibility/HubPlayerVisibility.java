package de.jeezyhub.inventories.visibility;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubPlayerVisibility {

    public void switcherEnabled(org.bukkit.event.player.PlayerInteractEvent e) {
            ItemStack playerVisibility = new ItemStack(Material.INK_SACK, 1, (byte) 1);
            ItemMeta playerVisibilityMeta = playerVisibility.getItemMeta();
            playerVisibilityMeta.setDisplayName("§9Player Visibility §7(§cDisabled§7)");
            playerVisibility.setItemMeta(playerVisibilityMeta);
            e.getPlayer().getInventory().setItem(0, playerVisibility);
    }
    public void switcherDisabled(org.bukkit.event.player.PlayerInteractEvent e) {
        ItemStack playerVisibility = new ItemStack(Material.INK_SACK, 1, (byte) 10);
        ItemMeta playerVisibilityMeta = playerVisibility.getItemMeta();
        playerVisibilityMeta.setDisplayName("§9Player Visibility §7(§aEnabled§7)");
        playerVisibility.setItemMeta(playerVisibilityMeta);
        e.getPlayer().getInventory().setItem(0, playerVisibility);
    }
}