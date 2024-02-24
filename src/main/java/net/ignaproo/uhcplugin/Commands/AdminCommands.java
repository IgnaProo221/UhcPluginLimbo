package net.ignaproo.uhcplugin.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.ignaproo.uhcplugin.Config.ConfigData;
import net.ignaproo.uhcplugin.Config.PlayersData;
import net.ignaproo.uhcplugin.Controller.GameController;
import net.ignaproo.uhcplugin.Utils.Ranks;
import net.ignaproo.uhcplugin.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.ignaproo.uhcplugin.Utils.Utils.chatColor;

@CommandAlias("uhc|admin|dev")
@CommandPermission("uhcplugin.admin") // Si se remueve todos los jugadores sin OP pueden usar el comando
public class AdminCommands extends BaseCommand {

    private static String command(String s) {
        return Utils.c(Utils.getPrefix() + s);
    }
    @Subcommand("chapter")
    @CommandCompletion("get|set @nothing")
    @Syntax("<capitulos>")
    public void chapterChanger(Player player, String type, String[] args) {
        if (type.equalsIgnoreCase("set")) {
            int s = Integer.parseInt(args[1]);
            ConfigData.setConfigValue("chapter", String.valueOf(s));
            player.sendMessage(chatColor("&aSe establecio correctamente el capitulo &c" + ConfigData.getChapter() + "&a."));
        } else if (type.equalsIgnoreCase("get")) {
            player.sendMessage(chatColor("&aCapitulo actual &c" + ConfigData.getChapter() + "&a."));
        }
    }
    @Subcommand("reload")
    @CommandCompletion("config|playerdata  @nothing")
    private void reload(Player player, String type) {
        if (type.equalsIgnoreCase("config")) {
            ConfigData.reloadConfig();
            player.sendMessage(chatColor("&aSe recargo correctamente el archivo de configuración."));
        } else if (type.equalsIgnoreCase("player")) {
            PlayersData.reloadPlayerData();
            player.sendMessage(chatColor("&aSe recargo correctamente el archivo de datos de jugadores."));
        } else {
            player.sendMessage(chatColor("&cEl tipo de archivo que ingresaste no existe."));
        }
    }

    @Subcommand("maxchapters")
    @CommandCompletion("get|set @nothing")
    @Syntax("<maxCaps>")
    public void chapterMaxChanger(Player player, String type, String[] args) {
        if (type.equalsIgnoreCase("set")) {
            int s = Integer.parseInt(args[1]);
            ConfigData.setConfigValue("maxChapters", String.valueOf(s));
            player.sendMessage(chatColor("&aSe establecio correctamente el maximo de capitulos a &c" + ConfigData.getMaxChapter() + "&a."));
        } else if (type.equalsIgnoreCase("get")) {
            player.sendMessage(chatColor("&aMaximo de capitulo actual &c" + ConfigData.getMaxChapter() + "&a."));
        }
    }

    @Subcommand("chapsduration")
    @CommandCompletion("get|set @nothing")
    @Syntax("<durationchaps>")
    public void chapterDurationChanger(Player player, String type, String[] args) {
        if (type.equalsIgnoreCase("set")) {
            int s = Integer.parseInt(args[1]);
            ConfigData.setConfigValue("chapterDuration", String.valueOf(s));
            player.sendMessage(chatColor("&aSe establecio correctamente el maximo de capitulos a &c" + ConfigData.getChapterDuration() + "&a."));
        } else if (type.equalsIgnoreCase("get")) {
            player.sendMessage(chatColor("&aMaximo de capitulo actual &c" + ConfigData.getChapterDuration() + "&a."));
        }
    }
    @Subcommand("rank")
    @CommandCompletion("@rankList @players @nothing")
    public void setRank(Player sender, String[] args) {
        if (args.length == 0) return;
        try {
            Player target = Bukkit.getPlayer(args[1]);
            assert target != null;
            PlayersData.setPlayerData(target, "rank", args[0]);
            sender.sendMessage(chatColor("&aTu rango ha sido cambiado a &r" + Ranks.getRank(target) + "&a."));
            Ranks.setRank(target);
        } catch (Exception e) {
            sender.sendMessage(chatColor("&cError en el comando."));
            sender.sendMessage(chatColor("&c/dpa rank <rango> <jugador>"));
        }
    }
    @Subcommand("game")
    @CommandCompletion("true|false @nothing")
    @Syntax("<gameStatus>")
    public void setGameStatus(Player player, String type) {
        if (type.equalsIgnoreCase("true")) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                GameController.start(pl);
            }
            player.sendMessage(chatColor("&aSe establecio correctamente el status del juego a: &ctrue&a."));
        }else if (type.equalsIgnoreCase("false")) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                GameController.stop(pl);
            }
            player.sendMessage(chatColor("&aSe establecio correctamente el status del juego a: &cfalse&a."));
        }
    }
    @Subcommand("game")
    @CommandCompletion("true|false @nothing")
    @Syntax("<gameStatus>")
    public void setPvpStatus(Player player, String type) {
        if (type.equalsIgnoreCase("true")) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                GameController.enablePVP(pl);
            }
        }else if (type.equalsIgnoreCase("false")) {
            for (Player pl : Bukkit.getOnlinePlayers()) {
                GameController.disablePVP(pl);
            }
        }
    }
}
