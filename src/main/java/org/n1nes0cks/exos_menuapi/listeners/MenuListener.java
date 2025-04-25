package org.n1nes0cks.exos_menuapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Action;
import org.n1nes0cks.exos_menuapi.menu.AbstractMenu;
import org.n1nes0cks.exos_menuapi.utils.ButtonUtils;

import java.util.HashMap;
import java.util.Map;

public class MenuListener implements Listener {

    private static final Map<String, AbstractMenu> openMenus = new HashMap<>();

    public static void registerMenu(Player player, AbstractMenu menu) {
        openMenus.put(player.getName(), menu);
    }

    public static void unregisterMenu(Player player) {
        openMenus.remove(player.getName());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String playerId = event.getWhoClicked().getName();
        AbstractMenu menu = openMenus.get(playerId);

        if (menu == null || !event.getInventory().equals(menu.getInventory())) return;

        event.setCancelled(true);
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        if (!ButtonUtils.isButton(clickedItem)) {
            menu.notButtonAction(event);
            return;
        }

        Action action = menu.getActions().get(ButtonUtils.getIdentifier(clickedItem));
        if (action != null) action.execute(event);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getReason() != InventoryCloseEvent.Reason.OPEN_NEW) unregisterMenu((Player) event.getPlayer());
    }
}