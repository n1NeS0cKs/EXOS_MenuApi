package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;

public class ButtonIS extends ItemStack {

    private static final NamespacedKey key = new NamespacedKey(EXOS_MenuApi.getPlugin(),"button_name");

    public ButtonIS(ItemStack itemStack, String tag) {
        super(itemStack);
        editMeta(meta -> meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, tag));
    }

    public ButtonIS(Material material, String tag) {
        super(material);
        editMeta(meta -> meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, tag));
    }

    private ButtonIS(ItemStack itemStack) {
        super(itemStack);
    }

    public static boolean isButton(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().has(key);
    }

    public static ButtonIS itemStackToButton(ItemStack itemStack) {
        if(isButton(itemStack)) return new ButtonIS(itemStack);
        throw new IllegalArgumentException("ItemStack is not button, because button_name is null");
    }

    public String getTag() {
        return getItemMeta().getPersistentDataContainer().get(key,PersistentDataType.STRING);
    }
}
