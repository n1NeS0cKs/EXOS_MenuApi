package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;

public class Button {

    private ButtonAction action;
    private String identifier;
    private ItemStack itemStack;
    private int slot;


    /**Используется только в классе меню, так как {@code identifier} присвается посли обработки через
     * {@link org.n1nes0cks.exos_menuapi.menu.MenuProcessor}**/
    public Button(ItemStack itemStack, int slot, ButtonAction action)  {
        this.action = action;
        this.itemStack = itemStack.clone();
        this.slot = slot;
    }

    /**Обычно не используется но, он нужнен для создания полноценной кнопки.
     * Это нужно для реализации в обход обработчика {@link org.n1nes0cks.exos_menuapi.menu.MenuProcessor}
     * **/
    public Button(String identifier, ItemStack itemStack, int slot, ButtonAction action)  {
        this.action = action;
        this.itemStack = itemStack.clone();
        this.slot = slot;
        this.identifier = identifier;
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(
                new NamespacedKey(EXOS_MenuApi.getInit(), "button_name"),
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

    public int getSlot() {
        return slot;
    }

    public void setIdentifier(String buttonName) {
        this.identifier = buttonName;
    }
}
