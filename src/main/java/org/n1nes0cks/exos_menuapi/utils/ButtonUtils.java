package org.n1nes0cks.exos_menuapi.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;

public class ButtonUtils {
    private static final NamespacedKey namespacedKey = new NamespacedKey(EXOS_MenuApi.init(), "button_name");

    public static boolean isButton(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespacedKey);
    }

    public static void setIdentifier(ItemStack itemStack,String identifier) {
        itemStack.editMeta(meta -> meta.getPersistentDataContainer().set(
                namespacedKey,
                PersistentDataType.STRING,
                identifier));
    }

    public static String getIdentifier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public static NamespacedKey getNamespacedKey() {
        return namespacedKey;
    }
}
