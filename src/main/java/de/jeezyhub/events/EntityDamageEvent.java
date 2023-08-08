package de.jeezyhub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageEvent implements Listener {

    @EventHandler
    public void onDamage(org.bukkit.event.entity.EntityDamageEvent e) {
        e.setCancelled(true);
    }
}