package de.jeezyhub.events;

import de.jeezyhub.inventories.front.HubFrontInventory;
import de.jeezyhub.scoreboard.Scoreboard;
import de.jeezyhub.utils.BungeeChannelApi;
import de.jeezyhub.utils.FakePlayerChecker;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static de.jeezyhub.utils.ArrayStorage.*;


public class JoinEvent implements Listener {

    HubFrontInventory hubFrontInventory = new HubFrontInventory();
    Scoreboard scoreboard = new Scoreboard();

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoinEvent(PlayerJoinEvent e) {
        if (FakePlayerChecker.isFakePlayer(e.getPlayer())) return;

        setPlayerVisibility(e);

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
        FastBoard board = new FastBoard(e.getPlayer());
        scoreboard.updateBoardOnJoin(board, e);
        boards.put(e.getPlayer().getUniqueId(), board);

        for (FastBoard updateBoard : boardsQueue.values()) {
            updateBoard.updateTitle(updateBoard.getTitle());
            updateBoard.updateLines(updateBoard.getLines());
            updateBoard.updateLine(2," §9§l♦ §fPlayers§7: §9"+bungeeChannelApi.allPlayerCount());
        }
    }

    private void setHubItems(PlayerJoinEvent e) {
        hubFrontInventory.setCompassOnJoin(e);
        hubFrontInventory.setSettingsOnJoin(e);
        hubFrontInventory.setPlayerVisibilityOnJoin(e);
    }

    private void setPlayerVisibility(PlayerJoinEvent e) {
        for (Player ps : Bukkit.getOnlinePlayers()) {
            e.getPlayer().hidePlayer(ps);
            ps.hidePlayer(e.getPlayer());
        }
    }
}