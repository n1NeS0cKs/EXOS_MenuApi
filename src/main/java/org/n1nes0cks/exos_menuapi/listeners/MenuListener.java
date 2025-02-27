package org.n1nes0cks.exos_menuapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Button;
import org.n1nes0cks.exos_menuapi.button.ButtonAction;
import org.n1nes0cks.exos_menuapi.menu.AbstractMenu;
import org.n1nes0cks.exos_menuapi.menu.SingleMenu;

public class MenuListener implements Listener {

    AbstractMenu menu;
    boolean cancelled;

    public MenuListener(AbstractMenu menu , boolean cancelled) {
        this.menu = menu;
        this.cancelled = cancelled;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack clickedItemStack = event.getCurrentItem();
        if(!event.getInventory().equals(menu.getInventory())) return;
        if(clickedItemStack == null) return;
        if(clickedItemStack.getItemMeta() == null) return;
        if(!Button.isButton(clickedItemStack)) return;
        event.setCancelled(cancelled);

        ButtonAction action = menu.getActions().get(Button.getIdentifier(clickedItemStack)) != null ?
                menu.getActions().get(Button.getIdentifier(clickedItemStack)) : null;
        if(action == null) return;
        action.execute(event);
    }

}
