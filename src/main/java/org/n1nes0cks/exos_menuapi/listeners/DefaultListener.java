package org.n1nes0cks.exos_menuapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.ButtonIS;
import org.n1nes0cks.exos_menuapi.menu.Menu;
import org.n1nes0cks.exos_menuapi.utils.AbstractListener;

public class DefaultListener extends AbstractListener {

    public DefaultListener(Menu menu) {
        super(menu);
    }

    @Override
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if(!event.getInventory().equals(getMenu().getInventory())) return;

        ItemStack item = event.getCurrentItem();
        if(item == null) return;

        event.setCancelled(true);

        if(!ButtonIS.isButton(item)) return;

        String tag = ButtonIS.itemStackToButton(item).getTag();

        getMenu().getActions().get(tag).action(event);
    }
}
