package de.jeezyhub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCommandPreprocessEvent implements Listener {

    @EventHandler
    public void onCommandsExecution(org.bukkit.event.player.PlayerCommandPreprocessEvent e) {

        if (e.getPlayer().hasPermission("jeezy.hub.commands.bypass")) {
            return;
        }

        if (e.getMessage().equalsIgnoreCase("/plugins") || e.getMessage().equalsIgnoreCase("/pl")) {
            e.setCancelled(true);
        }
    }
}