package org.n1nes0cks.exos_menuapi.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.button.Button;

import java.util.ArrayList;

public abstract class PagedMenu extends AbstractMenu {

    private int maxPage;
    private int currentPage;
    private ArrayList<ItemStack> items;

    public PagedMenu(String displayName, int size, int maxPage) {
        super(displayName, size, MenuType.PAGED);
        this.maxPage = maxPage;
        currentPage = 0;
        items = new ArrayList<>();
    }

    public PagedMenu(String displayName, int size) {
        super(displayName, size, MenuType.PAGED);
        this.maxPage = Integer.MAX_VALUE;
        currentPage = 0;
        items = new ArrayList<>();
    }

    public void nextPage() {
        if ((currentPage + 1) * size >= items.size()) return; // Проверяем, есть ли элементы
        if (currentPage < maxPage - 1) {
            currentPage++;
            update();
        }
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
            update();
        }
    }

    private void update() {
        inventory.clear();
        int firstSlot = size * currentPage;

        for (int slot = 0; slot < 45; slot++) {
            int itemIndex = firstSlot + slot;
            if (itemIndex < items.size()) {
                inventory.setItem(slot, items.get(itemIndex));
            }
        }
        Compile();
    }

    public void addButton(Button button) {
        if(items.size() >= maxPage * size) return;
        items.add(button.getItemStack());
        update();
    }

    public void removeButton(Button button) {
        if(items.isEmpty()) return;
        items.remove(button.getItemStack());
        update();
    }

    public void eraseButton() {
        if(items.isEmpty()) return;
        items.remove(items.size()-1);
        update();
    }


    public void open(Player player, int page) {
        currentPage = page;
        open(player);
    }

    public void open(Player player) {
        update();
        super.open(player);
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemStack> items) {
        this.items = items;
    }
}
