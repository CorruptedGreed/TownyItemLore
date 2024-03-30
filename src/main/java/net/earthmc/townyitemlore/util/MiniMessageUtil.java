package net.earthmc.townyitemlore.util;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MiniMessageUtil {
    public static List<Component> generateLore(Player player) {
        List<Component> lore = new ArrayList<>();
        MiniMessage miniMessage = MiniMessage.builder().build();
        lore.add(miniMessage.deserialize("<dark_gray>Historical Information"));
        lore.add(miniMessage.deserialize("<!i><yellow>Forged by: " + player.getName()));
        if (!TownyAPI.getInstance().isTownyWorld(player.getWorld())) {
            lore.add(miniMessage.deserialize("<!i><yellow>Origin: Unknown"));
        }
        Town town = TownyAPI.getInstance().getTown(player.getLocation());
        if (TownyAPI.getInstance().isWilderness(player.getLocation())) {
            lore.add(miniMessage.deserialize("<!i><yellow>Origin: Wilderness"));
        }
        else {
            assert town != null;
            Nation nation = town.getNationOrNull();
            if (nation == null) {
                lore.add(miniMessage.deserialize("<!i><yellow>Origin: Town of " + town.getName()));
            }
            else {
                lore.add(miniMessage.deserialize("<!i><yellow>Origin: Nation of " + nation.getName() + ", Town of " + town.getName()));
            }
        }
        lore.add(miniMessage.deserialize("<!i>Date: " + (TimeUtil.formattedDate)));
        return lore;
    }
}
