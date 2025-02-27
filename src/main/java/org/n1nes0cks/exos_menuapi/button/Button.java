package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;

public class Button {

    private final ButtonAction action;
    private ItemStack itemStack;
    private static final NamespacedKey namespacedKey = new NamespacedKey(EXOS_MenuApi.init(), "button_name");

    public Button(String identifier, ItemStack itemStack, ButtonAction action)  {
        this.action = action;
        this.itemStack = itemStack.clone();
        setButtonMeta(identifier);
    }

    public void execute(InventoryClickEvent event) {
        action.execute(event);
    }

    public String getIdentifier() {
        return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public static String getIdentifier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ButtonAction getAction() {
        return action;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setIdentifier(String identifier) {
        setButtonMeta(identifier);
    }

    private void setButtonMeta(String identifier) {
        this.itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(
                namespacedKey,
                PersistentDataType.STRING,
                identifier));
    }

    public static NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }

    public static boolean isButton(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey);
    }
}
