package net.earthmc.townyitemlore;

import net.earthmc.townyitemlore.listener.ItemCraftedListener;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownyItemLore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Get the plugin manager
        PluginManager pluginManager = getServer().getPluginManager();
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();

        }

        // Register the event listener
        if (pluginManager != null) {
            pluginManager.registerEvents(new ItemCraftedListener(), this);
            // Log a message indicating that the listener has been registered
            getLogger().info("CraftListener has been registered.");
        } else {
            getLogger().warning("Failed to register CraftListener! Plugin manager is null.");
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
