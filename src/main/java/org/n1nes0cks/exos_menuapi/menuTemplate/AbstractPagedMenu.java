package org.n1nes0cks.exos_menuapi.menuTemplate;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.n1nes0cks.exos_menuapi.Utils;

import java.util.List;

public abstract class AbstractPagedMenu extends AbstractMenu{

    private final List<ItemStack> itemStacks;
    private int currentPage = 0;

    public AbstractPagedMenu(int size, Component title, List<ItemStack> itemStacks) {
        super(size, title);
        this.itemStacks = itemStacks;
        updatePage();
        initializeItems();
    }

    public AbstractPagedMenu(int size, Component title) {
        super(size, title);
        this.itemStacks = null;
        updatePage();
        initializeItems();
    }

    public void updatePage() {
        int area = getWidth() * getHeight();
        int startItemIndex = currentPage * area;
        List<Integer> slots = Utils.selectInvSquare(getStartIndex(),getWidth(),getHeight());

        getInventory().clear();

        for (int i = 0; i < slots.size(); i++) {
            int itemIndex = startItemIndex + i;
            if (itemIndex < itemStacks.size()) {
                getInventory().setItem(slots.get(i), itemStacks.get(itemIndex));
            } else {
                break;
            }
        }
    }

    public void previousPage() {
        if(hasPreviousPage()){
            currentPage--;
            updatePage();
            initializeItems();
        }
    }

    public void nextPage() {
        if(hasNextPage()){
            currentPage++;
            updatePage();
            initializeItems();
        }
    }

    public int getStartIndex() {return 0;}
    public int getWidth() {return 9;}
    public int getHeight() {return 5;}
    public List<ItemStack> getItemStacks() {return itemStacks;}

    public int getTotalPages() {
        int itemsPerPage = getWidth() * getHeight();
        return (int) Math.ceil((double) itemStacks.size() / itemsPerPage);
    }

    public boolean hasNextPage() {
        return currentPage < getTotalPages() - 1;
    }

    public boolean hasPreviousPage() {
        return currentPage > 0;
    }
}
