package net.ignaproo.uhcplugin.Utils;

import net.ignaproo.uhcplugin.Config.PlayersData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class Ranks {
    private static Team getScoreboardRank(Rangos rank){
        try{
            Team newTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(rank.priority + "-" + rank.name());
            newTeam.setPrefix(Utils.c(rank.prefix));
            return newTeam;
        } catch (Exception e){
            return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(rank.priority + "-" + rank.name());
        }
    }

    public static void setRank(Player target){
        Team team = getScoreboardRank(getRank(target));
        team.addEntry(target.getName());
    }

    public static String getPrefix(Player target){
        return Utils.c(getRank(target).prefix);
    }

    public static Rangos getRank(Player target){
        String rank = PlayersData.getRank(target);
        for(Rangos r : Rangos.values()){
            if(r.name().equalsIgnoreCase(rank)){
                return r;
            }
        }
        return Rangos.DEFAULT;
    }

    public static List<String> rankList(){
        List<String> rangos = new ArrayList<>();
        for(Rangos r : Rangos.values()){
            rangos.add(r.name());
        }
        return rangos;
    }

    public enum Rangos{

        ADMIN(Utils.c("\uFA01 "), "01"),
        STAFF(Utils.c("\uFA02 "), "02"),
        MODERATOR(Utils.c("\uFA03 "), "03"),
        WINNER(Utils.c("\uFA04 "), "04"),
        DEFAULT(Utils.c("\uFA05 "), "05"),
        DEATH(Utils.c("\uFA06 "), "06"),
        SPECTATOR(Utils.c("\uFA07 ") , "07");

        private final String prefix;
        private final String priority;

        Rangos(String prefix, String priority){
            this.prefix = prefix;
            this.priority = priority;
        }
    }
}
