package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import org.n1nes0cks.exos_menuapi.button.Button;

public  class MenuListener implements Listener {

    AbstractMenu menu;

    public MenuListener(AbstractMenu menu) {
        this.menu = menu;
    }

    @EventHandler
    private void clickButton(InventoryClickEvent event) {
        if(!event.getInventory().equals(menu.getInventory())) return;
        ItemStack clickedItemStack = event.getCurrentItem();
        ItemMeta meta = clickedItemStack != null ? clickedItemStack.getItemMeta() : null;
        if(meta == null) return;
        NamespacedKey buttonKey = Button.getNamespacedKey();

        boolean isButton = meta.getPersistentDataContainer().has(buttonKey);
        if(!isButton) return;

        String buttonName = meta.getPersistentDataContainer().get(buttonKey, PersistentDataType.STRING);
        Button button = menu.getButtons().get(buttonName);
        button.execute(event);
    }
}
