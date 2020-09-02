package me.sean0402.projectlinks.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/*
 Created on 01/09/2020 at 19:10
 Author - Sean
*/
public class Utils {

    public static void sendMessage(Player player, String message) {
        player.sendMessage(colour(message));
    }

    /*public static void sendHoverClickMessage(Player p, String chatMessage, String hoverMessage, String clickMessage, boolean sendTitle) {
        p.spigot().sendMessage(new ComponentBuilder(colour(chatMessage))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colour(hoverMessage)).create()))
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, clickMessage)).create());
    }*/

    public static String colour(String message) {
        return message == null ? "String is null!" : ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorMessage(List<String> text) {
        return text.isEmpty() ? text : text.stream().map(Utils::colour).collect(Collectors.toList());
    }

    public static void sendConsoleMessage(String... messages) {
        for (String s : messages) Bukkit.getConsoleSender().sendMessage(colour(s));
    }

    public static void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        //todo: add 1.8 fade in timers.
        p.sendTitle(colour(title), colour(subTitle), fadeIn, stay, fadeOut);
    }

    private static void sendTitle(Player p, String title, String subTitle) {
        sendTitle(p, title, subTitle, 0, 20, 0);
    }
}
