package net.earthmc.townyitemlore.util;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.*;

import static net.earthmc.townyitemlore.util.LuckPermsUtil.getPlayerGroup;

public class LoreUtil {
    public static List<Component> generateLore(Player player) {
        List<Component> lore = new ArrayList<>();
        Collection<String> possibleGroups = Arrays.asList("premium", "mysterymaster");
        String playerGroup = getPlayerGroup(player, possibleGroups);

        MiniMessage miniMessage = MiniMessage.builder().build();

        lore.add(miniMessage.deserialize("<dark_gray>Historical Information"));

        if (playerGroup != null && !playerGroup.equals("premium") && !playerGroup.equals("mysterymaster")) {
            lore.add(miniMessage.deserialize("<!i><gray>Forged by: " + player.getName()));
        } else if (playerGroup != null && playerGroup.equals("premium")) {
            lore.add(miniMessage.deserialize("<!i><gray>Forged by: <gradient:#ffbf00:#ffdc73:#ffcf40>" + player.getName()));
        } else if (playerGroup != null) {
            lore.add(miniMessage.deserialize("<!i><gray>Forged by: <bold><gradient:#B61BE1:#D986F0:#B61BE1>" + player.getName()));
        }

        if (!TownyAPI.getInstance().isTownyWorld(player.getWorld())) {
            lore.add(miniMessage.deserialize("<!i><gray>Origin: Unknown"));
        }
        Town town = TownyAPI.getInstance().getTown(player.getLocation());
        if (TownyAPI.getInstance().isWilderness(player.getLocation())) {
            lore.add(miniMessage.deserialize("<!i><gray>Origin: Wilderness"));
        }
        else {
            assert town != null;
            Nation nation = town.getNationOrNull();
            if (nation == null) {
                lore.add(miniMessage.deserialize("<!i><gray>Origin: [<dark_aqua>" + town.getName() + "<gray>]"));
            }
            else {
                lore.add(miniMessage.deserialize("<!i><gray>Origin: [<gold>" + nation.getName() + "<gray>|<dark_aqua>" + town.getName() + "<gray>]"));
            }
        }
        lore.add(miniMessage.deserialize("<!i><gray>Date: " + (TimeUtil.formattedDate)));
        return lore;
    }
}
