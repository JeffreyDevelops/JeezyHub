package de.jeezyhub.utils;

import gg.mineral.server.fakeplayer.FakePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FakePlayerChecker {

    public static boolean isFakePlayer(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        return FakePlayer.isFakePlayer(craftPlayer.getHandle());
    }

}
