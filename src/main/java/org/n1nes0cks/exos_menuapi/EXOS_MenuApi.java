package org.n1nes0cks.exos_menuapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EXOS_MenuApi extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    @Override
    public void onDisable() {
    }

    public static Plugin getPlugin() {
        return EXOS_MenuApi.getPlugin(EXOS_MenuApi.class);
    }
}
