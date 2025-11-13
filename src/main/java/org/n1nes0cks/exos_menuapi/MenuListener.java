package org.n1nes0cks.exos_menuapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.menuTemplate.AbstractMenu;

import java.util.function.Consumer;

public class MenuListener implements Listener {

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof AbstractMenu menu)) return;
        ItemStack itemStack = event.getCurrentItem();
        if(itemStack == null) return;
        event.setCancelled(menu.cancelable());

        String tag = ButtonStack.getTag(itemStack);
        if(tag == null) return;

        Consumer<InventoryClickEvent> action = menu.getActionMap().get(tag);
        if (action != null) {
            action.accept(event);
        }
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        if(!(event.getInventory().getHolder() instanceof AbstractMenu menu)) return;
        menu.onClose();
    }

    @EventHandler
    private void onOpen(InventoryOpenEvent event) {
        if(!(event.getInventory().getHolder() instanceof AbstractMenu menu)) return;
        menu.onOpen();
    }
}
