package de.jeezyhub.events;

import de.jeezyhub.inventories.compass.HubCompassInsideInventory;
import de.jeezyhub.inventories.settings.HubSettingsInsideInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    HubCompassInsideInventory hubCompassInsideInventory = new HubCompassInsideInventory();

    HubSettingsInsideInventory hubSettingsInsideInventory = new HubSettingsInsideInventory();

    @EventHandler
    public void onPlayerInteraction(org.bukkit.event.player.PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
        switch (e.getItem().getItemMeta().getDisplayName()) {
            case "§9Gamemodes":
                hubCompassInsideInventory.openInv(e);
                break;
            case "§9Settings":
                hubSettingsInsideInventory.openInv(e);
                break;
        }
        }
    }
}