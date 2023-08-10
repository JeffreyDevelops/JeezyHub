package de.jeezyhub.utils;

import de.jeezycore.main.Main;
import org.bukkit.entity.Player;

public class BungeeChannelApi {
    int allPlayerCount = 0;
    public static int selectedPlayerCount = 0;
    io.github.leonardosnt.bungeechannelapi.BungeeChannelApi api = io.github.leonardosnt.bungeechannelapi.BungeeChannelApi.of(Main.getPlugin(Main.class));

    public void sendToServer(Player p) {
        api.connect(p, "practice-NA");
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

    public void getSelectedPlayerCount() {
        try {
            api.getPlayerCount("practice-NA")
                    .whenComplete((oneServerPlayerCount, error) -> {
                        selectedPlayerCount = oneServerPlayerCount;
                    });
        } catch (Exception e) {
        }
    }
}