package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.HashMap;


public abstract class AbstractMenu {

    private final HashMap<String, Button> buttons;
    private final Inventory inventory;

    public AbstractMenu() {
        inventory = Bukkit.createInventory(null, getSize(), getName());
        buttons = new HashMap<>();
        EXOS_MenuApi.getInit().getServer().getPluginManager().registerEvents(new MenuListener(this), EXOS_MenuApi.getInit());
    }


    public abstract int getSize();
    public abstract String getName();
    public abstract void Compile(); // for Buttons
    public void OnOpen() {}


    public void open(Player player) {
        OnOpen();
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<String, Button> getButtons() {
        return buttons;
    }

    public void addButton(int slot, Button button) {
        if(!buttons.containsKey(button.getIdentifier())) {
               buttons.put(button.getIdentifier(), button);
        }
        inventory.setItem(slot, button.getItemStack());

    }

    public void addButton(Button button) {
        if(!buttons.containsKey(button.getIdentifier())) {
            buttons.put(button.getIdentifier(), button);
        }
        try {
            inventory.setItem(inventory.firstEmpty(), button.getItemStack());
        } catch (Exception ignored) {}

    }

    public void removeButton(Button button) {
        if(inventory.contains(button.getItemStack())) {
            inventory.removeItem(button.getItemStack());
            if(inventory.contains(button.getItemStack())) return;
            buttons.remove(button.getIdentifier());
        }

    }

    public void removeButtonAll(Button button) {
        if (!buttons.containsKey(button.getIdentifier())) return;
        buttons.remove(button.getIdentifier());
        inventory.remove(button.getItemStack());
    }

    public void removeButton(int slot) {
        ItemStack item = inventory.getItem(slot);
        if(item == null) return;
        boolean isButton = !item.getItemMeta().getPersistentDataContainer().has(Button.getNamespacedKey());
        if(isButton) return;

        String identifier = item.getItemMeta().getPersistentDataContainer().get(
                Button.getNamespacedKey(),
                PersistentDataType.STRING);

            Button button = buttons.get(identifier);

        inventory.setItem(slot, null);

        if (inventory.contains(button.getItemStack())) return;
        buttons.remove(button.getIdentifier());

    }

}
