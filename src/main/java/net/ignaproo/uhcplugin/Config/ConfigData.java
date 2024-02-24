package net.ignaproo.uhcplugin.Config;

import net.ignaproo.uhcplugin.Utils.Utils;
import net.ignaproo.uhcplugin.main;

import java.util.Date;

public class ConfigData {
    private static final CreateFile config = new CreateFile(main.getInstance(), "config.yml");

    public static void setConfigValue(String id, String value) {
        config.setConfig(id, value);
    }

    public static void reloadConfig() {
        config.reloadConfig();
    }

    public static String getConfigValue(String id, String defaultValue) {
        if (config.getConfig().getString(id) == null) {
            config.setConfig(id, defaultValue);
        }
        return config.getConfig().getString(id);
    }

    public static String getServerDate() {
        return getConfigValue("date", Utils.dateFormat(new Date()));
    }

    public static String getChapter() {
        return getConfigValue("chapter", "1");
    }
    public static String getMaxChapter() {
        return getConfigValue("maxChapters", "10");
    }
    public static String getChapterDuration() {
        return getConfigValue("chapterDuration", "30");
    }
    public static String isOnGame() {
        return getConfigValue("isOnGame", "false");
    }
    public static String pvpEnable() {
        return getConfigValue("pvpEnabled", "false");
    }

    public static int getDay(){
        return Integer.parseInt(getConfigValue("day", "0"));
    }
}
