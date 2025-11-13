package org.n1nes0cks.exos_menuapi.menuTemplate;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractMenu implements InventoryHolder {
    private final Map<String, Consumer<InventoryClickEvent>> actionMap;
    private final Inventory inventory;

    public AbstractMenu(int size, Component title) {
        this.inventory = Bukkit.createInventory(this,size,title);
        actionMap = new HashMap<>();
        initializeActions();
        initializeItems();
    }

    public Map<String, Consumer<InventoryClickEvent>> getActionMap() {
        return actionMap;
    }

    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    protected abstract void initializeItems();
    protected abstract void initializeActions();
    public void onOpen() {}
    public void onClose() {}
    public boolean cancelable() {return true;}
}
