package org.n1nes0cks.exos_menuapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.ButtonAction;
import org.n1nes0cks.exos_menuapi.menu.AbstractMenu;

public class MenuListener implements Listener {

    AbstractMenu menu;

    public MenuListener(AbstractMenu menu) {
        this.menu = menu;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getInventory().equals(menu.getInventory())) return;
        if(event.getCurrentItem() == null) return;
        if(event.getCurrentItem().getItemMeta() == null) return;

        ItemStack clickedItemStack = event.getCurrentItem();

        ButtonAction action = menu.getButtons().get(clickedItemStack) != null ? menu.getButtons().get(clickedItemStack) : null;
        if(action == null) return;
        action.execute(event);
    }

}
