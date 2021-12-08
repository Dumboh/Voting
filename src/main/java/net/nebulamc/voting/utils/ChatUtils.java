package net.nebulamc.voting.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChatUtils {
    public static String colorizeHex(String message) {
        message = colorize(message);
        Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 32);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }

    public static List<String> colorizeList(List<String> list, String... replacements) {
        return list.stream().map(s -> colorizeHex(doReplacements(s, replacements))).collect(Collectors.toList());
    }

    public static void sendMultiLineMessage(Player player, List<String> messages, String... replacements) {
        for (String msg: messages) {
            colorizeAndSend(player, msg, replacements);
        }
    }

    public static String colorize(String string, String... replacements) {
        return ChatColor.translateAlternateColorCodes('&', doReplacements(string, replacements));
    }

    public static void colorizeAndSend(CommandSender commandSender, String message, String... replacements) {
        commandSender.sendMessage(colorize(ChatUtils.doReplacements(message, replacements)));
    }

    public static String doReplacements(String message, String... replacements) {
        if (replacements.length > 0) {
            for (Iterator<String> iterator = Arrays.asList(replacements).iterator(); iterator.hasNext();) {
                String key = iterator.next();
                if (iterator.hasNext()) {
                    message = message.replace(key, iterator.next());
                }
            }
        }

        return message;
    }
}
