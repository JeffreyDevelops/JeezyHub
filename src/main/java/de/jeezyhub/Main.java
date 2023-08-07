package de.jeezyhub;


import de.jeezyhub.colors.Color;
import de.jeezyhub.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyDevelopment]"+Color.GREEN_BOLD+" Successfully"+Color.CYAN_BOLD+" started JeezyHub coded by JeezyDevelopment!"+Color.RESET);
        EventsRegister();
    }

    private void EventsRegister() {
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BreakBlocksEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEvent(), this);
    }

    @Override
    public void onDisable() {
        System.out.println(Color.WHITE_BOLD+"[JeezyHub]"+Color.RED_BOLD+" shutting down..."+Color.RESET);
    }
}