package org.n1nes0cks.exos_menuapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class EXOS_MenuApi extends JavaPlugin {

    public static Plugin getInit() {
        return EXOS_MenuApi.getPlugin(EXOS_MenuApi.class);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
