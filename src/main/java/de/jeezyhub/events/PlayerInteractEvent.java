package de.jeezyhub.events;

import de.jeezyhub.inventories.join.HubInsideInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    HubInsideInventory hubInsideInventory = new HubInsideInventory();

    @EventHandler
    public void onPlayerInteraction(org.bukkit.event.player.PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Gamemodes")) {
            hubInsideInventory.openInv(e);
        }
    }
}