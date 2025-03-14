package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface Action {
    void execute(InventoryClickEvent event);
}
