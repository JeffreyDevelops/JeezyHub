package de.jeezyhub.events;


import de.jeezyhub.inventories.join.HubFrontInventory;
import de.jeezyhub.utils.FakePlayerChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    HubFrontInventory hubFrontInventory = new HubFrontInventory();

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        if (FakePlayerChecker.isFakePlayer(e.getPlayer())) return;

        setHubItems(e);

        e.getPlayer().setWalkSpeed(1);

        e.getPlayer().sendMessage(new String[] {
                "\n",
                " §9§lMineral Network §7§l(§6§lSeason §f§l1§7§l)\n",
                "\n",
                " §9§l♦ §f§lWebsite: §9§lmineral.gg\n",
                "\n",
                " §9§l♦ §f§lStore: §9§lstore.mineral.gg\n",
                "\n",
                " §9§l♦ §f§lDiscord: §9§ldiscord.mineral.gg\n",
                "\n",
                " §9§l♦ §f§lTwitter: §9§ltwitter.com/MineralServer\n",
                "\n"
        });
    }

    private void setHubItems(PlayerJoinEvent e) {
        hubFrontInventory.setCompassOnJoin(e);
    }
}