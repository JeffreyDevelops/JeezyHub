package de.jeezyhub.utils;

import de.jeezycore.main.Main;
import org.bukkit.entity.Player;

public class BungeeChannelApi {
    int allPlayerCount = 0;
    int selectedPlayerCount = 0;
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

    public int getSelectedPlayerCount() {
        try {
            api.getPlayerCount("practice-NA")
                    .whenComplete((oneServerPlayerCount, error) -> {
                        System.out.println(oneServerPlayerCount);
                        selectedPlayerCount = oneServerPlayerCount;
                        System.out.println(selectedPlayerCount);
                    });
        } catch (Exception e) {
        }
        return selectedPlayerCount;
    }
}