package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.*;

public abstract class SingleMenu extends AbstractMenu {

    public SingleMenu(String displayName, int size) {
        super(displayName,size);
    }

    public void setButton(Button button, Collection<Integer> slots) {
        for (int slot : slots) {
            inventory.setItem(slot, button.getItemStack());
        }
        actions.put(button.getIdentifier(), button.getAction());
    }

    public void setButton(Button button, int... slots) {
        setButton(button, Arrays.asList(Arrays.stream(slots).boxed().toArray(Integer[]::new)));

    }

    public void removeButton(Button button) {
        inventory.remove(button.getItemStack());
        if (!actions.containsKey(button.getIdentifier())) return;
        actions.remove(button.getIdentifier());
    }

    public void removeButton(int... slots) {
        for (int slot : slots) {
            ItemStack itemStack = inventory.getItem(slot);
            if (itemStack == null) continue;

            String identifier = Button.getIdentifier(itemStack);
            inventory.clear(slot); // Очищаем слот

            if (identifier == null || !actions.containsKey(identifier)) continue;
            actions.remove(identifier);
        }
    }

    /*public Button itemStackToButton(ItemStack itemStack) {
        if(!itemStack.getItemMeta().getPersistentDataContainer().has(Button.getNamespacedKey())) return null;
        String identifier = itemStack.getItemMeta().getPersistentDataContainer().get(Button.getNamespacedKey(),PersistentDataType.STRING);
        if(!buttons.containsKey(identifier)) return null;
        return buttons.get(identifier);
    }*/
}
