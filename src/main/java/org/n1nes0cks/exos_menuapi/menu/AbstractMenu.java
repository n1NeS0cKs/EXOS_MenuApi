package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.n1nes0cks.exos_menuapi.EXOS_MenuApi;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.HashMap;


public abstract class AbstractMenu {

    private HashMap<String, Button> buttons = new HashMap<>();
    private Inventory inventory;

    public AbstractMenu(int size, String name) {
        inventory = Bukkit.createInventory(null, size, name);
    }

    public AbstractMenu(Inventory inventory) {
        this.inventory = inventory;
    }

    public void compile(Plugin plugin) {
        inventory.clear();
        MenuProcessor.register(this);
        for(Button button : buttons.values()) {
            inventory.setItem(button.getSlot(),button.getItemStack());
        }
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(this), EXOS_MenuApi.getInit());
    }

    public void update() {
        inventory.clear();
        for(Button button : buttons.values()) {
            inventory.setItem(button.getSlot(),button.getItemStack());
        }
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HashMap<String, Button> getButtons() {
        return buttons;
    }

    public void addButton(Button button) {
        if(!buttons.containsKey(button.getIdentifier())) {
            buttons.put(button.getIdentifier(), button);
        }
        else {
            Bukkit.getLogger().warning("Кнопка " + button.getIdentifier() + " уже существует");
        }
    }

    public void removeButton(Button button) {
        if(buttons.containsKey(button.getIdentifier()))
            buttons.remove(button.getIdentifier());
    }

}
