package org.n1nes0cks.exos_menuapi;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ButtonStack extends ItemStack {
    private static final NamespacedKey namespace = new NamespacedKey(EXOS_MenuApi.getPlugin(),"tag");

    public ButtonStack(Material material, String tag) {
        super(material);
        editMeta(meta -> meta.getPersistentDataContainer().set(namespace, PersistentDataType.STRING,tag));
    }
    public ButtonStack(ItemStack itemStack, String tag) {
        super(itemStack);
        editMeta(meta -> meta.getPersistentDataContainer().set(namespace, PersistentDataType.STRING,tag));
    }
    public String getTag() {
        return getItemMeta().getPersistentDataContainer().get(namespace,PersistentDataType.STRING);
    }
    public void setTag(String tag) {
        editMeta(meta -> meta.getPersistentDataContainer().set(namespace, PersistentDataType.STRING,tag));
    }
    public ItemStack getRaw() {
        ItemStack rawItemStack = this.clone();
        rawItemStack.editMeta(meta->meta.getPersistentDataContainer().remove(namespace));
        return rawItemStack;
    }
    public static boolean isButtonStack(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().has(namespace);
    }

    public static String getTag(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta()) return null;
        return itemStack.getItemMeta().getPersistentDataContainer().get(namespace, PersistentDataType.STRING);
    }
}
