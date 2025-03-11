package de.jeezyhub.events;

import de.jeezyhub.inventories.compass.HubCompassInsideInventory;
import de.jeezyhub.inventories.settings.HubSettingsInsideInventory;
import de.jeezyhub.inventories.visibility.HubPlayerVisibility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    HubCompassInsideInventory hubCompassInsideInventory = new HubCompassInsideInventory();

    HubSettingsInsideInventory hubSettingsInsideInventory = new HubSettingsInsideInventory();

    HubPlayerVisibility hubPlayerVisibility = new HubPlayerVisibility();

    @EventHandler
    public void onPlayerInteraction(org.bukkit.event.player.PlayerInteractEvent e) {

        if (e.getItem() == null || e.getItem().getItemMeta() == null || e.getItem().getItemMeta().getDisplayName() == null) {
            return;
        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                switch (e.getItem().getItemMeta().getDisplayName()) {
                    case "§9§lGamemodes":
                        hubCompassInsideInventory.openInv(e);
                        break;
                    case "§9Settings":
                        hubSettingsInsideInventory.openInv(e);
                        break;
                    case "§9Player Visibility §7(§aEnabled§7)":
                        hubPlayerVisibility.switcherEnabled(e);
                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            e.getPlayer().showPlayer(ps);

                        }
                        break;
                    case "§9Player Visibility §7(§cDisabled§7)":
                        hubPlayerVisibility.switcherDisabled(e);
                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            e.getPlayer().hidePlayer(ps);
                        }
                        break;
                }
        }
    }
}