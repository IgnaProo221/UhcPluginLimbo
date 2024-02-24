package net.ignaproo.uhcplugin.Controller;

import net.ignaproo.uhcplugin.Config.ConfigData;
import net.ignaproo.uhcplugin.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GameController {
    public static void start(Player p) {
        ConfigData.setConfigValue("isOnGame", "true");
        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);
        wb.setSize(1000);
    }
    public static void stop(Player p) {
        ConfigData.setConfigValue("isOnGame", "false");
        WorldBorder wb = Bukkit.getWorld("world").getWorldBorder();
        wb.setCenter(0, 0);
        wb.setSize(100);
    }
    public static void deathMatch(Player p) {
        p.sendMessage("");
    }
    public static void enablePVP(Player p) {
        p.sendMessage(Utils.c(Utils.getPrefix() + "El pvp se ha activado &ccuidado."));
        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 0.7F);
        ConfigData.setConfigValue("pvpEnabled", "true");
    }
    public static void disablePVP(Player p) {
        p.sendMessage(Utils.c(Utils.getPrefix() + "El pvp se ha activado &ccuidado."));
        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1, 0.7F);
        ConfigData.setConfigValue("pvpEnabled", "false");
    }
}
