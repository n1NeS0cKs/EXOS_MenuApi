package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.Button;
import org.n1nes0cks.exos_menuapi.button.ButtonAction;
import org.n1nes0cks.exos_menuapi.listeners.MenuListener;

import java.util.*;

public abstract class AbstractMenu {

    private final Inventory inventory;
    private String displayName;
    private int size;
    private final HashMap<String, ButtonAction> actions; // HashMap<String, ButtonAction> (addButton(itenstack){setIndetifir})

    public AbstractMenu (String displayName, int size, boolean cancelled) {
        inventory = Bukkit.createInventory(null,size, displayName);
        this.displayName = displayName;
        this.size = size;
        this.actions = new HashMap<>();
        EXOS_MenuApi.init().getServer().getPluginManager().registerEvents(new MenuListener(this, cancelled), EXOS_MenuApi.init());
    }

    protected abstract void Compile();

    public void open(Player player) {
        Compile();
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSize() {
        return size;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setButton(Button button, int  ... slots) {
        for (int slot : slots) {
            inventory.setItem(slot, button.getItemStack());
        }
        actions.put(button.getIdentifier(), button.getAction());
    }

    public void setButton(Button button, ArrayList<Integer> slots) {
        for (int slot : slots) {
            inventory.setItem(slot, button.getItemStack());
        }
        actions.put(button.getIdentifier(), button.getAction());
    }

    public void removeButton(Button... buttons) {
        for(Button button : buttons) {
            while (inventory.contains(button.getItemStack())) {
                inventory.remove(button.getItemStack());
            }
            if (!actions.containsKey(button.getIdentifier())) return;
            actions.remove(button.getIdentifier());
        }
    }

    public void removeButton(int... slots) {
        for(int slot : slots) {
            if(inventory.getItem(slot) == null) continue;
            ItemStack itemStack = inventory.getItem(slot);
            String identifier = Button.getIdentifier(itemStack);
            if(inventory.contains(itemStack)) return;
            if(!actions.containsKey(identifier)) return;
            actions.remove(identifier);
        }
    }

    public HashMap<String, ButtonAction> getActions() {
        return actions;
    }

    /*public Button itemStackToButton(ItemStack itemStack) {
        if(!itemStack.getItemMeta().getPersistentDataContainer().has(Button.getNamespacedKey())) return null;
        String identifier = itemStack.getItemMeta().getPersistentDataContainer().get(Button.getNamespacedKey(),PersistentDataType.STRING);
        if(!buttons.containsKey(identifier)) return null;
        return buttons.get(identifier);
    }*/
}
