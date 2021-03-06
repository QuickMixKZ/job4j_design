package ru.job4j.design.isp.menu;

public class IndentMenuPrinter implements MenuPrinter {

    public static final String INDENT = "----";

    @Override
    public void print(Menu menu) {
        menu.forEach(menuItemInfo -> {
            StringBuilder s = new StringBuilder();
            int level = (int) menuItemInfo.getNumber().chars().filter(value -> value == '.').count();
            s.append(INDENT.repeat(Math.max(0, level - 1)));
            s.append(menuItemInfo.getNumber());
            s.append(menuItemInfo.getName());
            menuItemInfo.getActionDelegate().delegate(s.toString());
        });
    }

}
