package org.n1nes0cks.exos_menuapi.utils;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.n1nes0cks.exos_menuapi.menu.Menu;

public abstract class AbstractListener implements Listener {

    private final Menu menu;

    public AbstractListener(Menu menu) {
        this.menu = menu;
    }

    public abstract void inventoryClick(InventoryClickEvent event);

    public Menu getMenu() {
        return menu;
    }
}
