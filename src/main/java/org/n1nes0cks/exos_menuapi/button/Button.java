package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;

public class Button {

    private final ButtonAction action;
    private String identifier;
    private ItemStack itemStack;
    private static final NamespacedKey namespacedKey = new NamespacedKey(EXOS_MenuApi.getInit(), "button_name");

    public Button(String identifier, ItemStack itemStack, ButtonAction action)  {
        this.action = action;
        this.itemStack = itemStack.clone();
        this.identifier = identifier;
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey,
                PersistentDataType.STRING, identifier
        );
        this.itemStack.setItemMeta(meta);
    }

    public void execute(InventoryClickEvent event) {
        action.execute(event);
    }

    public String getIdentifier() {
        return identifier;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(
                Button.getNamespacedKey(),
                PersistentDataType.STRING, identifier
        );
        this.itemStack.setItemMeta(meta);
    }

    public static NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }
}
