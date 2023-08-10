package de.jeezyhub.queue;

import de.jeezyhub.utils.BungeeChannelApi;
import org.bukkit.entity.Player;
import java.util.Timer;
import java.util.TimerTask;
import static de.jeezyhub.utils.ArrayStorage.queueStorage;

public class QueueManager {
    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    Timer time = new Timer();

    public void add(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (inQueue(e)) return;
        if (e.getWhoClicked().hasPermission("jeezy.hub.bypass.queue")) {
            bungeeChannelApi.sendToServer((Player) e.getWhoClicked());
            return;
        }
        queueStorage.add((Player) e.getWhoClicked());
        System.out.println(queueStorage);
        e.getWhoClicked().sendMessage("§7You §2successfully §7entered the queue place: §7[§9"+(queueStorage.size())+"§7].");
    }

    private void remove(int index) {
        queueStorage.remove(queueStorage.get(index));
    }

    private boolean inQueue(org.bukkit.event.inventory.InventoryClickEvent e) {
        if (queueStorage.contains((Player) e.getWhoClicked())) {
            e.getWhoClicked().sendMessage("§7You have §calready §7joined the queue!");
        }
        return queueStorage.contains((Player) e.getWhoClicked());
    }

    private void sendToServer(int index) {
        bungeeChannelApi.sendToServer(queueStorage.get(index));
    }


    int indexAddUp = 0;
    public void scheduleServerEntry() {
        TimerTask timeSchedule = new TimerTask() {
            @Override
            public void run() {

                if (queueStorage.isEmpty()) {
                    return;
                }

                sendToServer(indexAddUp);
                remove(indexAddUp);
                indexAddUp --;
                indexAddUp++;
            }
        };
        time.scheduleAtFixedRate(timeSchedule, 10000, 10000);
    }
}