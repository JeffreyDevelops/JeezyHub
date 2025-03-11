package de.jeezyhub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static de.jeezyhub.utils.ArrayStorage.loginServer;

public class PlayerLoginEvent implements Listener {

    @EventHandler
    public void onLoginEvent(org.bukkit.event.player.PlayerLoginEvent e) {
        System.out.println(e.getHostname().substring(0, e.getHostname().indexOf(":")).startsWith("mineral") ? "na.mineral.gg" : e.getHostname().substring(0, e.getHostname().indexOf(":")));
        loginServer.put(e.getPlayer().getUniqueId(), e.getHostname().substring(0, e.getHostname().indexOf(":")).startsWith("mineral") ? "na.mineral.gg" : e.getHostname().substring(0, e.getHostname().indexOf(":")));
    }

}
