package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.ButtonAction;
import org.n1nes0cks.exos_menuapi.listeners.MenuListener;

import java.util.HashMap;

public abstract class AbstractMenu {
    private final Inventory inventory;
    private String displayName;
    private int size;
    private final HashMap<String, ButtonAction> actions;

    public AbstractMenu(String displayName, int size, boolean cancelled) {
        inventory = Bukkit.createInventory(null,size, displayName);
        this.displayName = displayName;
        this.size = size;
        this.actions = new HashMap<>();
        EXOS_MenuApi.init().getServer().getPluginManager().registerEvents(new MenuListener(this, cancelled), EXOS_MenuApi.init());
    }

    protected abstract void Compile();

    public void open(Player player) {
        Compile();
        player.openInventory(getInventory());
    }

    public HashMap<String, ButtonAction> getActions() {
        return actions;
    }

    public int getSize() {
        return size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
