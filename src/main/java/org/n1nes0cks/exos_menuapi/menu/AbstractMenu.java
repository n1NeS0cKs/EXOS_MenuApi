package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.Utils;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class AbstractMenu {

    private ArrayList<ItemStack> items;
    private final HashMap<String, Button> buttons;
    private final Inventory inventory;

    public AbstractMenu() {
        inventory = Bukkit.createInventory(null, getSize(), getName());
        items = Utils.toArrayList(inventory.getContents());
        buttons = new HashMap<>();
        inventory.setContents(Utils.toArray(items, ItemStack.class));
        EXOS_MenuApi.getInit().getServer().getPluginManager().registerEvents(new MenuListener(this), EXOS_MenuApi.getInit());
    }


    public abstract int getSize();
    public abstract String getName();
    public abstract void OnOpen(); // for Buttons


    public void update() {
        inventory.clear();
        inventory.setContents(Utils.toArray(items,ItemStack.class));
    }

    public void open(Player player) {
        OnOpen();
        player.openInventory(inventory);
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<String, Button> getButtons() {
        return buttons;
    }

    public void addButton(int slot, Button button) {
           if(!buttons.containsKey(button.getIdentifier())) {
               buttons.put(button.getIdentifier(), button);
        }
           items.set(slot,button.getItemStack());
        update();
    }

    public void removeButton(Button button) {
        items.set(items.indexOf(button.getItemStack()),null);
        if (!items.contains(button.getItemStack())){
            if(!buttons.containsKey(button.getIdentifier())) return;
            buttons.remove(button.getIdentifier());
        }
        update();
    }

    public void removeButtonAll(Button button) {
        for(ItemStack item : items){
            if(item == null) continue;
            if(!item.getItemMeta().getPersistentDataContainer().has(
                    new NamespacedKey(EXOS_MenuApi.getInit(),"button_name"))
            ) return;
            String identifier = item.getItemMeta().getPersistentDataContainer().get(
                    new NamespacedKey(EXOS_MenuApi.getInit(),"button_name"),
                    PersistentDataType.STRING);
            if(button.getIdentifier().equals(identifier)){
                items.set(items.indexOf(button.getItemStack()),null);
            }
        }
        if (buttons.containsKey(button.getIdentifier())){
            buttons.remove(button.getIdentifier());
        }
        update();
    }

    public void removeButton(int slot) {
        ItemStack item = items.get(slot);
        if(item == null) return;
        String identifier = item.getItemMeta().getPersistentDataContainer().get(
                new NamespacedKey(EXOS_MenuApi.getInit(),"button_name"),
                PersistentDataType.STRING);
        Button button = buttons.get(identifier);
        items.set(slot,null);
        if(!items.contains(button.getItemStack())) {
            if(!buttons.containsKey(button.getIdentifier())) return;
            buttons.remove(button.getIdentifier());
        }
        update();

    }

}
