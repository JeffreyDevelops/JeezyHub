package de.jeezyhub.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceItemsEvent implements Listener {

    @EventHandler
    public void onItemPlacement(BlockPlaceEvent e) {
        if (e.getPlayer().isOp() && e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            return;
        e.setCancelled(true);
    }
}