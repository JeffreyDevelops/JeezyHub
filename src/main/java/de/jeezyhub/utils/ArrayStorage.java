package de.jeezyhub.utils;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArrayStorage {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static final Map<UUID, FastBoard> boardsQueue = new HashMap<>();

   public static HashMap<UUID, Inventory> perPlayerInventory = new HashMap<>();

   public static HashMap<UUID, Boolean> perPlayerInventoryOpened = new HashMap<>();

   public static ArrayList<Player> queueStorage = new ArrayList<>();

}
