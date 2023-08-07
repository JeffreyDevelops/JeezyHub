package de.jeezyhub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageByEntityEvent implements Listener {

    @EventHandler
    public void onPvp(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
        e.setCancelled(true);
    }

}
