package de.jeezyhub.scoreboard;

import de.jeezycore.db.FriendsSQL;
import de.jeezycore.db.RanksSQL;
import de.jeezyhub.utils.BungeeChannelApi;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.event.player.PlayerJoinEvent;
import static de.jeezycore.utils.ArrayStorage.friendsListData;


public class Scoreboard {
    RanksSQL ranksSQL = new RanksSQL();
    FriendsSQL friendsSQL = new FriendsSQL();
    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    String friendsSize;


    public void updateBoardOnJoin(FastBoard board, PlayerJoinEvent e) {
        friendsSQL.getAllFriendsData(e);
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
                "§fPlayers§7: §9"+ bungeeChannelApi.allPlayerCount(),
                "§fRank§7: " + show_rank_color+ranksSQL.rankNameInformation,
                "§fFriends§7: " +"§2§l"+friendsSize,
                "",
                "§9mineral.gg",
                "§7§m-----------------"
        );
        friendsListData.clear();
    }
}