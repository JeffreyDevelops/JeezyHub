package de.jeezyhub;


import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

import java.util.logging.Logger;

@Plugin(
        id = "jeezyhubvelocity",
        name = "JeezyHub",
        version = "1.0",
        url = "https://mineral.gg",
        description = "I did it!",
        authors = {"MineralStudio"},
        dependencies = {
                @Dependency(id = "redisbungee")
        }
)
public class Velocity {

    // RedisBungeeAPI api = RedisBungeeAPI.getRedisBungeeApi();

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public Velocity(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        logger.info("Successfully launched MineralVelocity.");
    }

    @Subscribe
    public void onCommandExecute(CommandExecuteEvent event) {

        if (event.getCommand().contains("lpv")) {
            //System.out.println(api.getServerToPlayers());
            event.setResult(CommandExecuteEvent.CommandResult.forwardToServer("/commandnotfound"));
        }
    }
}