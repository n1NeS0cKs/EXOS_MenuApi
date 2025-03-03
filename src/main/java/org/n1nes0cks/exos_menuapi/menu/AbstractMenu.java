package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.ButtonAction;
import org.n1nes0cks.exos_menuapi.listeners.MenuListener;

import java.util.HashMap;

public abstract class AbstractMenu {
    protected final Inventory inventory;
    protected String displayName;
    protected int size;
    protected final HashMap<String, ButtonAction> actions;
    protected final MenuType menuType;

    public AbstractMenu(String displayName, int size, MenuType menuType) {
        inventory = Bukkit.createInventory(null,size, displayName);
        this.displayName = displayName;
        this.size = size;
        this.actions = new HashMap<>();
        this.menuType = menuType;
    }

    protected abstract void Compile();

    public void open(Player player) {
        Compile();
        MenuListener.registerMenu(player, this); // Регистрируем меню
        player.openInventory(getInventory());
    }

    public void close(Player player) {
        MenuListener.unregisterMenu(player);
        player.closeInventory();
    }

    public void refresh(Player player) {
        player.updateInventory();
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

    public MenuType getMenuType() {
        return menuType;
    }

    public enum MenuType {
        SINGLE,
        PAGED
    }
}


