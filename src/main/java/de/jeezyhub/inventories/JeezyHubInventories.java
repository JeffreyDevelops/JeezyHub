package de.jeezyhub.inventories;

import de.jeezyhub.inventories.join.HubInsideInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@SuppressWarnings("SpellCheckingInspection")
public class JeezyHubInventories implements Listener {
    @EventHandler
    public void onCLickEvent(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null || e.getClickedInventory() == null) {
            return;
        }

        HubInsideInventory hubInsideInventory = new HubInsideInventory();
        hubInsideInventory.run(e);

    }
}
