package de.jeezyhub.queue;

import de.jeezyhub.scoreboard.Scoreboard;
import de.jeezyhub.utils.BungeeChannelApi;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Timer;
import java.util.TimerTask;

import static de.jeezyhub.utils.ArrayStorage.*;

public class QueueManager {
    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    public void add(org.bukkit.event.inventory.InventoryClickEvent e, String serverName) {
        if (inQueue(e)) return;
        if (e.getWhoClicked().hasPermission("jeezy.hub.bypass.queue")) {
            bungeeChannelApi.sendToServer((Player) e.getWhoClicked(), serverName);
            return;
        }
        queueStorage.add((Player) e.getWhoClicked());
        queueServer.add(serverName);
        System.out.println(queueStorage);
        e.getWhoClicked().sendMessage("§7You §2successfully §7entered the queue place: §7[§9"+(queueStorage.size())+"§7].");
        e.getWhoClicked().closeInventory();

        Scoreboard scoreboard = new Scoreboard();
        FastBoard board = new FastBoard((Player) e.getWhoClicked());
        scoreboard.updateBoardOnQueueEntry(board, e);
        boardsQueue.put(e.getWhoClicked().getUniqueId(), board);

        QueueManager queueManager = new QueueManager();
        for (FastBoard updateBoard : boardsQueue.values()) {
            updateBoard.updateTitle(updateBoard.getTitle());
            updateBoard.updateLines(updateBoard.getLines());
            updateBoard.updateLine(7," §9§l♦ §f§l"+queueManager.queuePlace(updateBoard.getPlayer())+" §7§l/ §9§l"+queueStorage.size());
        }
    }

    private void remove(int index) {
        queueStorage.remove(queueStorage.get(index));
        queueServer.remove(index);
    }

    private boolean inQueue(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (queueStorage.contains((Player) e.getWhoClicked())) {
            e.getWhoClicked().sendMessage("§7You have §calready §7joined the queue!");
        }
        return queueStorage.contains((Player) e.getWhoClicked());
    }

    public int queuePlace(Player p) {
        return queueStorage.indexOf(p.getPlayer()) + 1;
    }

    private void sendToServer(int index, String serverName) {
        bungeeChannelApi.sendToServer(queueStorage.get(index), serverName);
    }


    int indexAddUp = 0;
    public void scheduleServerEntry() {

        QueueManager queueManager = new QueueManager();

        Timer time = new Timer();
        TimerTask timeSchedule = new TimerTask() {
            @Override
            public void run() {

                if (queueStorage.isEmpty()) {
                    return;
                }

                sendToServer(indexAddUp, queueServer.get(indexAddUp));
                remove(indexAddUp);
                indexAddUp--;
                indexAddUp++;

                for (FastBoard updateBoard : boardsQueue.values()) {
                    updateBoard.updateTitle(updateBoard.getTitle());
                    updateBoard.updateLines(updateBoard.getLines());
                    updateBoard.updateLine(7," §9§l♦ §f§l"+queueManager.queuePlace(updateBoard.getPlayer())+" §7§l/ §9§l"+queueStorage.size());
                }
            }
        };
        time.scheduleAtFixedRate(timeSchedule, 10000, 10000);
    }
}