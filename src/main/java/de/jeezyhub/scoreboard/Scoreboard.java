package de.jeezyhub.scoreboard;

import de.jeezyhub.utils.BungeeChannelApi;
import fr.mrmicky.fastboard.FastBoard;

public class Scoreboard {

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    public void updateBoard(FastBoard board) {
        board.updateTitle("§9§lMineral §f§lHub");
        board.updateLines(
                "§7§m-----------------",
                "§fPlayers§7: §9"+ bungeeChannelApi.allPlayerCount(),
                "§fFriends§7: " + "§cNone",
                "§fRank§7: " + "§cNone",
                "",
                "§9mineral.gg",
                "§7§m-----------------"
        );
    }
}