package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface ButtonAction {
    public void execute(InventoryClickEvent event);
}
