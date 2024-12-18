package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.Button;
import org.n1nes0cks.exos_menuapi.button.Unregister;

import java.lang.reflect.Field;

public class MenuProcessor {

    public static boolean register(AbstractMenu menu) {
        Class<?> menuClazz = menu.getClass();
        Field[] fields = menuClazz.getDeclaredFields();

        for(Field field : fields) {
            field.setAccessible(true);
            if(field.getType() == Button.class) {
                try {
                    Button button = (Button)field.get(menu);
                    if(button.getIdentifier() == null) {
                        button.setIdentifier(field.getName());
                        ItemMeta meta = button.getItemStack().getItemMeta();
                        meta.getPersistentDataContainer().set(
                                new NamespacedKey(EXOS_MenuApi.getInit(), "button_name"),
                                PersistentDataType.STRING, button.getIdentifier()
                        );
                        button.getItemStack().setItemMeta(meta);
                    }
                        if (field.isAnnotationPresent(Unregister.class)) continue;
                        menu.addButton(button);

                }catch (Exception e) {e.printStackTrace();}
            }
        }
        return true;
    }

}
