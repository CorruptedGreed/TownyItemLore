package net.earthmc.townyitemlore.manager;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import net.earthmc.townyitemlore.util.MaterialUtil;
import net.earthmc.townyitemlore.util.TimeUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemLoreManager {

    public static void applyLore(ItemStack itemStack, Player player) {
        Material itemType = itemStack.getType();
        if (MaterialUtil.targetMaterials.contains(itemType)) {
            ItemMeta meta = itemStack.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Historical Information");
            lore.add(ChatColor.YELLOW + "Forged by: " + player.getName());
            if (TownyAPI.getInstance().isWilderness(player.getLocation())) {
                lore.add(ChatColor.YELLOW + "Origin: Wilderness");
            } else {
                Town town = TownyAPI.getInstance().getTown(player.getLocation());
                assert town != null;
                Nation nation = town.getNationOrNull();
                if (nation == null) {
                    lore.add(ChatColor.YELLOW + "Origin: Town of " + String.valueOf(town));
                } else {
                    lore.add(ChatColor.YELLOW + "Origin: Nation of " + String.valueOf(nation) + ", Town of " + String.valueOf(town));
                }
            }
            lore.add(ChatColor.YELLOW + "Date: " + String.valueOf(TimeUtil.formattedDate)); // Lore line with the formatted date
            meta.setLore(lore);
            itemStack.setItemMeta(meta);
        }
    }
}
