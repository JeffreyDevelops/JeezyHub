package de.jeezyhub.utils;

import de.jeezycore.main.Main;
import org.bukkit.entity.Player;

public class BungeeChannelApi {
    int allPlayerCount = 0;
    public static int selectedPlayerCount = 0;
    io.github.leonardosnt.bungeechannelapi.BungeeChannelApi api = io.github.leonardosnt.bungeechannelapi.BungeeChannelApi.of(Main.getPlugin(Main.class));

    public void sendToServer(Player p, String serverName) {
        api.connect(p, serverName);
    }

    public int allPlayerCount() {
        try {
            api.getPlayerCount("ALL")
                    .whenComplete((playerCounts, error) -> {
                        allPlayerCount = playerCounts;
                    });
        } catch (Exception e) {
        }
    return allPlayerCount;
    }

    public void getSelectedPlayerCount(String serverName) {
        try {
            api.getPlayerCount(serverName)
                    .whenComplete((oneServerPlayerCount, error) -> {
                        selectedPlayerCount = oneServerPlayerCount;
                    });
        } catch (Exception e) {
        }
    }

    public Integer receivedServerCount(String serverName) {
        this.getSelectedPlayerCount(serverName);
        return selectedPlayerCount;
    }
}