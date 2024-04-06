package net.earthmc.townyitemlore.util;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static net.earthmc.townyitemlore.util.LuckPermsUtil.getPlayerGroup;

public class LoreUtil {
    public static List<Component> generateLore(Player player) {
        List<Component> lore = new ArrayList<>();

        lore.add(parseMiniMessage("<dark_gray>Historical Information"));
        addForgedByToLore(player, lore);
        addOriginToLore(player, lore);
        lore.add(parseMiniMessage("<!i><gray>Date: " + (TimeUtil.formattedDate)));

        return lore;
    }


    public static void addForgedByToLore(Player player, List<Component> lore) {
        Collection<String> possibleGroups = Arrays.asList("premium", "mysterymaster");
        String playerGroup = getPlayerGroup(player, possibleGroups);
        String playerName = player.getName();

        if (playerGroup != null) {
            String forgedByMessage = "<!i><gray>Forged by: ";
            if (!playerGroup.equals("premium") && !playerGroup.equals("mysterymaster")) {
                lore.add(parseMiniMessage(forgedByMessage + playerName));
            } else {
                String gradientColor;
                if (playerGroup.equals("premium")) {
                    gradientColor = "<gradient:#ffbf00:#ffdc73:#ffcf40>";
                } else {
                    gradientColor = "<bold><gradient:#B61BE1:#D986F0:#B61BE1>";
                }
                lore.add(parseMiniMessage(forgedByMessage + gradientColor + playerName));
            }
        }
    }

    public static void addOriginToLore(Player player, List<Component> lore) {
        String originMessage = "<!i><gray>Origin: ";
        String unknownOrigin = "Unknown";
        String wildernessOrigin = "Wilderness";
        String darkAqua = "<dark_aqua>";
        String gold = "<gold>";
        String gray = "<gray>";

        if (!TownyAPI.getInstance().isTownyWorld(player.getWorld())) {
            lore.add(parseMiniMessage(originMessage + unknownOrigin));
        } else {
            Town town = TownyAPI.getInstance().getTown(player.getLocation());
            if (TownyAPI.getInstance().isWilderness(player.getLocation())) {
                lore.add(parseMiniMessage(originMessage + wildernessOrigin));
            } else {
                assert town != null;
                Nation nation = town.getNationOrNull();
                String townName = darkAqua + town.getName() + gray;
                if (nation == null) {
                    lore.add(parseMiniMessage(originMessage + "[" + townName + "]"));
                } else {
                    String nationName = gold + nation.getName() + gray;
                    lore.add(parseMiniMessage(originMessage + "[" + nationName + "|" + townName + "]"));
                }
            }
        }
    }

    public static Component parseMiniMessage(String message) {
        MiniMessage miniMessage = MiniMessage.builder().build();
        return miniMessage.deserialize(message);
    }
}
