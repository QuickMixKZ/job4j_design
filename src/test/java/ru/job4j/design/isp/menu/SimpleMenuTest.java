package ru.job4j.design.isp.menu;

import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenIndentPrint() {
        ActionDelegate delegate = new ActionDelegate() {
            StringBuilder stringBuilder = new StringBuilder();
            @Override
            public void delegate(String s) {
                stringBuilder.append(s);
                stringBuilder.append(System.lineSeparator());
            }

            @Override
            public String toString() {
                return stringBuilder.toString();
            }
        };
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", delegate);
        menu.add(Menu.ROOT, "Покормить собаку", delegate);
        menu.add("Сходить в магазин", "Купить продукты", delegate);
        menu.add("Купить продукты", "Купить хлеб", delegate);
        menu.add("Купить продукты", "Купить молоко", delegate);
        IndentMenuPrinter menuPrinter = new IndentMenuPrinter();
        menuPrinter.print(menu);
        String actual = delegate.toString();
        StringBuilder expected = new StringBuilder();
        expected.append("1.Сходить в магазин").append(System.lineSeparator());
        expected.append("----1.1.Купить продукты").append(System.lineSeparator());
        expected.append("--------1.1.1.Купить хлеб").append(System.lineSeparator());
        expected.append("--------1.1.2.Купить молоко").append(System.lineSeparator());
        expected.append("2.Покормить собаку").append(System.lineSeparator());
        assertEquals(expected.toString(), actual);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddAndSelectNonExistent() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить помидоры"), STUB_ACTION, "1."
                ),
                menu.select("Купить помидоры").get()
        );
    }

}