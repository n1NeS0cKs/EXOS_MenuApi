package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Action;
import org.n1nes0cks.exos_menuapi.listeners.MenuListener;

import java.util.HashMap;

public abstract class AbstractMenu {
    protected final Inventory inventory;
    protected String displayName;
    protected int size;

    public AbstractMenu(String displayName, int size) {
        inventory = Bukkit.createInventory(null,size, displayName);
        this.displayName = displayName;
        this.size = size;
    }

    protected abstract HashMap<Integer,ItemStack> getButtons();

    protected void setupButtons() {
        for (Integer i : getButtons().keySet()) {
            inventory.setItem(i, getButtons().get(i));
        }
    }

    public void open(Player player) {
        setupButtons();
        MenuListener.registerMenu(player, this); // Регистрируем меню
        player.openInventory(getInventory());
    }

    public void close(Player player) {
        MenuListener.unregisterMenu(player);
        player.closeInventory();
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

    public abstract HashMap<String, Action> getActions();

    public void notButtonAction(InventoryClickEvent event) {}

}


