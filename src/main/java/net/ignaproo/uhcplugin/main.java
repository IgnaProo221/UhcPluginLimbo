package net.ignaproo.uhcplugin;

import co.aikar.commands.BukkitCommandManager;
import net.ignaproo.uhcplugin.Commands.AdminCommands;
import net.ignaproo.uhcplugin.Events.DamageEvent;
import net.ignaproo.uhcplugin.Events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class main extends JavaPlugin {
    private static main instance;

    @Override
    public void onEnable() {
        instance = this; // don't move this.
        createCapsule();
        BukkitCommandManager mn = new BukkitCommandManager(this);
        mn.registerCommand(new AdminCommands());
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static main getInstance() {
        return instance;
    }

    public static void createCapsule() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "fill -8 225 -8 8 225 8 glass");
        Bukkit.dispatchCommand(console, "fill 8 226 -8 -8 229 -8 glass");
        Bukkit.dispatchCommand(console, "fill -8 229 -8 -8 226 8 glass");
        Bukkit.dispatchCommand(console, "fill -8 226 8 8 229 8 glass");
        Bukkit.dispatchCommand(console, "fill 8 229 8 8 226 -8 glass");

    }
}
