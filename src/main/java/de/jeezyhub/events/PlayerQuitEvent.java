package de.jeezyhub.events;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static de.jeezyhub.utils.ArrayStorage.boards;

public class PlayerQuitEvent implements Listener {

    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent e) {

        FastBoard board = boards.remove(e.getPlayer().getUniqueId());

        if (board != null) {
            board.delete();
        }
    }
}
