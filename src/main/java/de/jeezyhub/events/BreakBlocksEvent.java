package de.jeezyhub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlocksEvent implements Listener {

    @EventHandler
    public void BreakBlocksEvent(BlockBreakEvent e) {
        e.setCancelled(true);
    }

}
