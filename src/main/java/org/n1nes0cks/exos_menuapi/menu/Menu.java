package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.ButtonActon;
import org.n1nes0cks.exos_menuapi.listeners.DefaultListener;
import org.n1nes0cks.exos_menuapi.utils.AbstractListener;

import java.util.HashMap;

public class Menu {

    private final HashMap<String, ButtonActon> actions;
    private final Inventory inventory;

    public Menu(Inventory inventory) {
        this.inventory = inventory;
        this.actions = new HashMap<>();
        EXOS_MenuApi.getPlugin().getServer().getPluginManager().registerEvents(new DefaultListener(this), EXOS_MenuApi.getPlugin());
    }

    public Menu(Inventory inventory, AbstractListener listener) {
        this.inventory = inventory;
        this.actions = new HashMap<>();
        EXOS_MenuApi.getPlugin().getServer().getPluginManager().registerEvents(listener, EXOS_MenuApi.getPlugin());
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<String, ButtonActon> getActions() {
        return actions;
    }

}
