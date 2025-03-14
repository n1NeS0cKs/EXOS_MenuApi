package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public abstract class PagedMenu extends SingleMenu {

    private int maxPage;
    private int currentPage;
    private ArrayList<ItemStack> items;

    public PagedMenu(String displayName, int size, int maxPage) {
        super(displayName, size);
        this.maxPage = maxPage;
        currentPage = 0;
        items = new ArrayList<>();
    }

    public PagedMenu(String displayName, int size) {
        super(displayName, size);
        this.maxPage = Integer.MAX_VALUE;
        currentPage = 0;
        items = new ArrayList<>();
    }

    public PagedMenu(String displayName, int size, ArrayList<ItemStack> items) {
        super(displayName, size);
        this.maxPage = Integer.MAX_VALUE;
        currentPage = 0;
        this.items = new ArrayList<>(items);
    }

    public final void nextPage() {
        if ((currentPage + 1) * (size - 9) >= items.size()) return; // Проверяем, есть ли элементы
        if (currentPage < maxPage - 1) {
            currentPage++;
            update();
        }
    }

    public final void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            update();
        }
    }

    public void update() {
        inventory.clear();
        int firstSlot = size * currentPage;

        for (int slot = 0; slot < size - 9; slot++) {
            int itemIndex = firstSlot + slot;
            if (itemIndex < items.size()) {
                inventory.setItem(slot, items.get(itemIndex));
            }
        }
        setupButtons();
    }

    public final void addButton(ItemStack itemStack) {
        int maxItems = maxPage * (size - 9); // Учитываем, что последние 9 слотов — кнопки
        if (maxPage != Integer.MAX_VALUE && items.size() >= maxItems) return;

        items.add(itemStack);
        update();
    }

    @Override
    public final void removeButton(ItemStack itemStack) {
        super.removeButton(itemStack);
        if (items.isEmpty()) return;

        items.removeIf(item -> item.equals(itemStack)); // Удаляет все вхождения предмета
        update();
    }

    public final void eraseButton() {
        if (items.isEmpty()) return;

        items.remove(items.size() - 1);
        if (!items.isEmpty()) {
            update();
        }
    }


    public void open(Player player, int page) {
        if (page < 0) page = 0;
        if (page >= maxPage) page = maxPage - 1;

        currentPage = page;
        open(player);
    }

    public void open(Player player) {
        update();
        super.open(player);
    }

    public final ArrayList<ItemStack> getItems() {
        return items;
    }

    public final void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }
}
