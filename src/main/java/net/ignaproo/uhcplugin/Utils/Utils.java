package net.ignaproo.uhcplugin.Utils;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void console(String msg) {
        System.out.println(chatColor(msg));
    }
    public static String Timer(int Seconds, boolean IncludeDays) {

        int num, hor, min, seg, day;
        num = Seconds;


        if (IncludeDays == true) {
            day = num / (3600 * 24);
            hor = ((num - (day * (3600 * 24))) / 3600);
            min = (num - (3600 * hor)) / 60;
            seg = num - ((day * (3600 * 24)) + (hor * 3600) + (min * 60));
            String diasreales = (String.format("%02d", day));
            String horasreales = (String.format("%02d", hor));
            String minutosreales = (String.format("%02d", min));
            String segundosreales = (String.format("%02d", seg));
            return diasreales + ":" + horasreales + ":" + minutosreales + ":" + segundosreales;


        } else {


            hor = num / 3600;
            min = (num - (3600 * hor)) / 60;
            seg = num - (((hor * 3600) + (min * 60)));

            String horasreales = (String.format("%02d", hor));
            String minutosreales = (String.format("%02d", min));
            String segundosreales = (String.format("%02d", seg));
            return horasreales + ":" + minutosreales + ":" + segundosreales;

        }


    }

    public static String ib(java.awt.Color Colora, java.awt.Color Colorb, String StringToGradient) {
        ChatColor Color2 = ChatColor.of(Colorb);
        ChatColor Color1 = ChatColor.of(Colora);
        int r = Color1.getColor().getRed();
        int g = Color1.getColor().getGreen();
        int b = Color1.getColor().getBlue();
        int r2 = Color2.getColor().getRed();
        int g2 = Color2.getColor().getGreen();
        int b2 = Color2.getColor().getBlue();
        String Hex = String.format("%02X%02X%02X", r, g, b);
        String Hex2 = String.format("%02X%02X%02X", r2, g2, b2);
        String Final = IridiumColorAPI.process("<GRADIENT:" + Hex + ">" + StringToGradient + "</GRADIENT:" + Hex2 + ">");


        return Final;


    }
    public static String solid(java.awt.Color color) {
        int r1 = color.getRed();
        int g2 = color.getGreen();
        int b3 = color.getBlue();
        String Final = IridiumColorAPI.process("<SOLID:" + String.format("%02X%02X%02X", r1, g2, b3) + ">");
        return Final;
    }
    public static String c(String s) {
        return IridiumColorAPI.process(s);
    }

    public static String chatColor(String message) {
        return message.replace("&", "§");
    }


    public static String dateFormat(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String serverHour(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
    public static String getPrefix() {
        return Utils.c("&8&l[" + Utils.ib(new java.awt.Color(217, 186, 30), new java.awt.Color(197, 125, 0), "&lUhcCore") + "&8&l]&r ");
    }
}
