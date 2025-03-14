package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;

public abstract class SingleMenu extends AbstractMenu {

    public SingleMenu(String displayName, int size) {
        super(displayName,size);
    }

    public final void setButton(ItemStack itemStack, Collection<Integer> slots) {
        for (int slot : slots) {
            inventory.setItem(slot, itemStack);
        }
    }

    public final void setButton(ItemStack itemStack, int... slots) {
        setButton(itemStack, Arrays.asList(Arrays.stream(slots).boxed().toArray(Integer[]::new)));
    }

    public  void removeButton(ItemStack itemStack) {
        inventory.remove(itemStack);
    }

    public final void removeButton(int... slots) {
        for (int slot : slots) {
            ItemStack itemStack = inventory.getItem(slot);
            if (itemStack == null) continue;
            inventory.clear(slot); // Очищаем слот
        }
    }

    /*public Button itemStackToButton(ItemStack itemStack) {
        if(!itemStack.getItemMeta().getPersistentDataContainer().has(Button.getNamespacedKey())) return null;
        String identifier = itemStack.getItemMeta().getPersistentDataContainer().get(Button.getNamespacedKey(),PersistentDataType.STRING);
        if(!buttons.containsKey(identifier)) return null;
        return buttons.get(identifier);
    }*/
}
