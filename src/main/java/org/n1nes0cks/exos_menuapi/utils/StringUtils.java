package org.n1nes0cks.exos_menuapi.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static Component stringToComponent(String input) {
        // Если входная строка пуста, возвращаем пустой компонент
        if (input == null || input.isEmpty()) {
            return Component.empty();
        }

        // Сброс форматирования и установка белого цвета
        String processed = "&r&f" + input;

        // Регулярное выражение для обработки HEX-цветов (&#FFFFFF)
        Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");

        // Заменяем HEX-цвета на формат MiniMessage (<#FFFFFF>)
        Matcher matcher = hexPattern.matcher(processed);
        while (matcher.find()) {
            String hexCode = matcher.group(1);
            processed = processed.replace(matcher.group(), "<#" + hexCode + ">");
        }

        // Заменяем стандартные цветовые и форматирующие коды (&)
        processed = processed.replace("&0", "<black>")
                .replace("&1", "<dark_blue>")
                .replace("&2", "<dark_green>")
                .replace("&3", "<dark_aqua>")
                .replace("&4", "<dark_red>")
                .replace("&5", "<dark_purple>")
                .replace("&6", "<gold>")
                .replace("&7", "<gray>")
                .replace("&8", "<dark_gray>")
                .replace("&9", "<blue>")
                .replace("&a", "<green>")
                .replace("&b", "<aqua>")
                .replace("&c", "<red>")
                .replace("&d", "<light_purple>")
                .replace("&e", "<yellow>")
                .replace("&f", "<white>")
                .replace("&k", "<obfuscated>")
                .replace("&l", "<bold>")
                .replace("&m", "<strikethrough>")
                .replace("&n", "<underline>")
                .replace("&o", "<italic>")
                .replace("&r", "<reset>");
        return net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(processed).decoration(TextDecoration.ITALIC, false);

    }

    public static ArrayList<Component> stringToComponent(ArrayList<String> strings) {
        if(strings == null || strings.isEmpty()) return null;
        ArrayList<Component> ComponentArray = new ArrayList<>();
        for(String s : strings) {
           ComponentArray.add(stringToComponent(s));
        }
        return ComponentArray;
    }

    public static ArrayList<Component> stringToComponent(@NotNull String... strings) {
        ArrayList<Component> ComponentArray = new ArrayList<>();
        for(String s : strings) {
            ComponentArray.add(stringToComponent(s));
        }
        return ComponentArray;
    }

    public static String replace(String source, String key, String data) {
        return source.replace(key,data);
    }


    public static ArrayList<String> replace(ArrayList<String> source, String key, String data) {
        ArrayList<String> replaceStrings = new ArrayList<>();

        for(String s : source) {
            replaceStrings.add(s.replace(key,data));
        }
        return replaceStrings;
    }

    public static String locationToString(Location location) {
        return "world:"+location.getWorld().getName()+"_" +
                "x:"+location.getBlockX()+"_" +
                "y:"+location.getBlockY()+"_" +
                "z:"+location.getBlockZ();
    }

    public static Location stringToLocation(String string) {
        Pattern pattern = Pattern.compile("world:(\\w+)_x:(\\d+)_y:(\\d+)_z:(\\d+)");
        Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {
            World world = Bukkit.getWorld(matcher.group(1));
            int x = Integer.parseInt(matcher.group(2));
            int y = Integer.parseInt(matcher.group(3));
            int z = Integer.parseInt(matcher.group(4));

            return new Location(world, x, y, z);
        } else {
            System.out.println("Ошибка: строка не соответствует формату!");
            return null;
        }
    }
}

