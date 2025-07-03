package org.n1nes0cks.exos_menuapi.utils;

import org.n1nes0cks.exos_menuapi.menu.Menu;

public abstract class AbstractMenuManager {

    private final Menu menu;

    public AbstractMenuManager(Menu menu) {
        this.menu = menu;
    }

    public abstract void build();

    public Menu getMenu() {
        return menu;
    }
}
