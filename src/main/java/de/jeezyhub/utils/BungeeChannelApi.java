package de.jeezyhub.utils;

import de.jeezycore.main.Main;
import org.bukkit.entity.Player;

public class BungeeChannelApi {

    io.github.leonardosnt.bungeechannelapi.BungeeChannelApi api = io.github.leonardosnt.bungeechannelapi.BungeeChannelApi.of(Main.getPlugin(Main.class));

    public void sendToServer(Player p) {
        api.connect(p, "practice-NA");
    }
}