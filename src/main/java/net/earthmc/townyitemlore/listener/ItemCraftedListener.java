package net.earthmc.townyitemlore.listener;

import net.earthmc.townyitemlore.manager.ItemLoreManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemCraftedListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = event.getCurrentItem();
        if (result != null) {
            ItemLoreManager.applyLore(result, (Player) event.getWhoClicked());
        }
    }
}