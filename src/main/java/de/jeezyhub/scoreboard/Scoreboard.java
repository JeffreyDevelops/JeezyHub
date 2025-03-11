package de.jeezyhub.scoreboard;

import de.jeezycore.db.FriendsSQL;
import de.jeezycore.db.RanksSQL;
import de.jeezyhub.queue.QueueManager;
import de.jeezyhub.utils.BungeeChannelApi;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import static de.jeezycore.utils.ArrayStorage.friendsListData;
import static de.jeezyhub.utils.ArrayStorage.loginServer;
import static de.jeezyhub.utils.ArrayStorage.queueStorage;


public class Scoreboard {
    RanksSQL ranksSQL = new RanksSQL();
    FriendsSQL friendsSQL = new FriendsSQL();
    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    QueueManager queueManager = new QueueManager();

    String friendsSize;


    public void updateBoardOnJoin(FastBoard board, PlayerJoinEvent e) {
        friendsSQL.getAllFriendsData(e.getPlayer());
        ranksSQL.getPlayerInformation(e.getPlayer());
        String sql = "SELECT * FROM ranks WHERE rankName = '"+ranksSQL.rankNameInformation+"'";
        ranksSQL.displayChatRank(sql);

        String show_rank_color = ranksSQL.rankColor.replace("&", "§");

        if (ranksSQL.rankNameInformation == null) {
            ranksSQL.rankNameInformation = "Default";
        }

        if (friendsListData.isEmpty()) {
            friendsSize = "§cNone";
        } else {
            friendsSize = String.valueOf(friendsListData.size());
        }

        board.updateTitle("§9§lMineral §f§lHub");
        board.updateLines(
                "§7§m-----------------",
                "§9§lProfile§7:",
                " §9§l♦ §fPlayers§7: §9"+ bungeeChannelApi.allPlayerCount(),
                " §9§l♦ §fRank§7: " + show_rank_color+ranksSQL.rankNameInformation,
                " §9§l♦ §fFriends§7: " +"§2§l"+friendsSize,
                "",
                "§9"+loginServer.get(e.getPlayer().getUniqueId()),
                "§7§m-----------------"
        );
        friendsListData.clear();
    }

    public void updateBoardOnQueueEntry(FastBoard board, org.bukkit.event.inventory.InventoryClickEvent e) {
        friendsSQL.getAllFriendsData((Player) e.getWhoClicked());
        ranksSQL.getPlayerInformation((Player) e.getWhoClicked());
        String sql = "SELECT * FROM ranks WHERE rankName = '"+ranksSQL.rankNameInformation+"'";
        ranksSQL.displayChatRank(sql);

        String show_rank_color = ranksSQL.rankColor.replace("&", "§");

        if (ranksSQL.rankNameInformation == null) {
            ranksSQL.rankNameInformation = "Default";
        }

        if (friendsListData.isEmpty()) {
            friendsSize = "§cNone";
        } else {
            friendsSize = String.valueOf(friendsListData.size());
        }

        board.updateTitle("§9§lMineral §f§lHub");
        board.updateLines(
                "§7§m-----------------",
                "§9§lProfile§7:",
                " §9§l♦ §fPlayers§7: §9"+ bungeeChannelApi.allPlayerCount(),
                " §9§l♦ §fRank§7: " + show_rank_color+ranksSQL.rankNameInformation,
                " §9§l♦ §fFriends§7: " +"§2§l"+friendsSize,
                "",
                "§9§lQueue§7:",
                " §9§l♦ §f§l"+queueManager.queuePlace((Player) e.getWhoClicked())+" §7§l/ §9§l"+queueStorage.size(),
                "",
                "§9mineral.gg",
                "§7§m-----------------"
        );
        friendsListData.clear();
    }

}