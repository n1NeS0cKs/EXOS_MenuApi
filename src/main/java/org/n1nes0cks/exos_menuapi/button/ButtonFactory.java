package org.n1nes0cks.exos_menuapi.button;

import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.utils.ButtonUtils;

public class ButtonFactory {

    public static ItemStack createButton(ItemStack itemStack, String identifier) {
        ItemStack cloneStack = itemStack.clone();
        ButtonUtils.setIdentifier(cloneStack, identifier);
        return cloneStack;
    }


}
