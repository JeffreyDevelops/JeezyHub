package de.jeezyhub.inventories;

import de.jeezyhub.inventories.compass.HubCompassInsideInventory;
import de.jeezyhub.inventories.settings.HubSettingsInsideInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.net.UnknownHostException;

@SuppressWarnings("SpellCheckingInspection")
public class JeezyHubInventories implements Listener {
    @EventHandler
    public void onCLickEvent(org.bukkit.event.inventory.InventoryClickEvent e) throws UnknownHostException {
        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getClickedInventory() == null) {
            return;
        }

        HubCompassInsideInventory hubCompassInsideInventory = new HubCompassInsideInventory();
        hubCompassInsideInventory.run(e);

        HubSettingsInsideInventory hubSettingsInsideInventory = new HubSettingsInsideInventory();
        hubSettingsInsideInventory.run(e);

    }
}
