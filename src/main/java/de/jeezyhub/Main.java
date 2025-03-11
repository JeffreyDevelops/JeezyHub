package de.jeezyhub;


import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.jeezyhub.colors.Color;
import de.jeezyhub.events.*;
import de.jeezyhub.inventories.JeezyHubInventories;
import de.jeezyhub.queue.QueueManager;
import de.jeezyhub.utils.BungeeChannelApi;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static de.jeezyhub.utils.ArrayStorage.boards;
import static de.jeezyhub.utils.ArrayStorage.boardsQueue;

public class Main extends JavaPlugin {

    BungeeChannelApi bungeeChannelApi = new BungeeChannelApi();

    QueueManager queueManager = new QueueManager();

    @Override
    public void onEnable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyDevelopment]"+Color.GREEN_BOLD+" Successfully"+Color.CYAN_BOLD+" started JeezyHub coded by JeezyDevelopment!"+Color.RESET);
        EventsRegister();
        queueManager.scheduleServerEntry();
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                board.updateTitle(board.getTitle());
                board.updateLines(board.getLines());
                board.updateLine(2," §9§l♦ §fPlayers§7: §9"+bungeeChannelApi.allPlayerCount());
            }
        }, 0, 20);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boardsQueue.values()) {
                board.updateTitle(board.getTitle());
                board.updateLines(board.getLines());
                board.updateLine(2," §9§l♦ §fPlayers§7: §9"+bungeeChannelApi.allPlayerCount());
            }
        }, 0, 20);
    }

    private void EventsRegister() {
        getServer().getPluginManager().registerEvents(new PlayerLoginEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        getServer().getPluginManager().registerEvents(new BreakBlocksEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new JeezyHubInventories(), this);
        getServer().getPluginManager().registerEvents(new DropItemsEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new PlaceItemsEvent(), this);
    }


    @Override
    public void onDisable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyHub]"+Color.RED_BOLD+" shutting down..."+Color.RESET);
    }

}