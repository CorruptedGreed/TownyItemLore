package net.earthmc.townyitemlore.util;

import org.bukkit.entity.Player;

import java.util.Collection;

public class LuckPermsUtil {
    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            if (player.hasPermission("group." + group)) {
                return group;
            }
        }
        return null;
    }
}
