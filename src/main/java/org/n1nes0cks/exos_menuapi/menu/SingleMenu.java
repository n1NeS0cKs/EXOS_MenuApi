package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.*;

public abstract class SingleMenu extends AbstractMenu{

    public SingleMenu(String displayName, int size, boolean cancelled) {
        super(displayName,size,cancelled);
    }

    public void setButton(Button button, int  ... slots) {
        for (int slot : slots) {
            getInventory().setItem(slot, button.getItemStack());
        }
        getActions().put(button.getIdentifier(), button.getAction());
    }

    public void setButton(Button button, ArrayList<Integer> slots) {
        for (int slot : slots) {
            getInventory().setItem(slot, button.getItemStack());
        }
        getActions().put(button.getIdentifier(), button.getAction());
    }

    public void removeButton(Button... buttons) {
        for(Button button : buttons) {
            while (getInventory().contains(button.getItemStack())) {
                getInventory().remove(button.getItemStack());
            }
            if (!getActions().containsKey(button.getIdentifier())) return;
            getActions().remove(button.getIdentifier());
        }
    }

    public void removeButton(int... slots) {
        for(int slot : slots) {
            if(getInventory().getItem(slot) == null) continue;
            ItemStack itemStack = getInventory().getItem(slot);
            String identifier = Button.getIdentifier(itemStack);
            if(getInventory().contains(itemStack)) return;
            if(!getActions().containsKey(identifier)) return;
            getActions().remove(identifier);
        }
    }
    
    /*public Button itemStackToButton(ItemStack itemStack) {
        if(!itemStack.getItemMeta().getPersistentDataContainer().has(Button.getNamespacedKey())) return null;
        String identifier = itemStack.getItemMeta().getPersistentDataContainer().get(Button.getNamespacedKey(),PersistentDataType.STRING);
        if(!buttons.containsKey(identifier)) return null;
        return buttons.get(identifier);
    }*/
}
