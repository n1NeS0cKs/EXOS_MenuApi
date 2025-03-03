package org.n1nes0cks.exos_menuapi;

import org.bukkit.command.Command;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.n1nes0cks.exos_menuapi.listeners.MenuListener;

public final class EXOS_MenuApi extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin init() {
        return EXOS_MenuApi.getPlugin(EXOS_MenuApi.class);
    }
}
