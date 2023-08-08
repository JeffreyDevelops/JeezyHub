package de.jeezyhub;


import de.jeezyhub.colors.Color;
import de.jeezyhub.events.*;
import de.jeezyhub.inventories.JeezyHubInventories;
import de.jeezyhub.scoreboard.Scoreboard;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.plugin.java.JavaPlugin;
import static de.jeezyhub.utils.ArrayStorage.boards;

public class Main extends JavaPlugin {

    Scoreboard scoreboard = new Scoreboard();


    @Override
    public void onEnable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyDevelopment]"+Color.GREEN_BOLD+" Successfully"+Color.CYAN_BOLD+" started JeezyHub coded by JeezyDevelopment!"+Color.RESET);
        EventsRegister();
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                scoreboard.updateBoard(board);
            }
        }, 0, 20);
    }

    private void EventsRegister() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BreakBlocksEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new JeezyHubInventories(), this);
    }

    @Override
    public void onDisable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyHub]"+Color.RED_BOLD+" shutting down..."+Color.RESET);
    }
}