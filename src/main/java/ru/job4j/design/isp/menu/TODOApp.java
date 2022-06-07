package ru.job4j.design.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) throws IOException {
        Menu menu = new SimpleMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        while (run) {
            System.out.println("Print parent element name:");
            String parent = reader.readLine();
            System.out.println("Print child element name:");
            String child = reader.readLine();
            if (!menu.add(parent.isEmpty() ? Menu.ROOT : parent, child, STUB_ACTION)) {
                System.out.println("No such parent element!");
            }
            System.out.println("Continue? (Y/N)");
            run = reader.readLine().equalsIgnoreCase("Y");
        }
        IndentMenuPrinter menuPrinter = new IndentMenuPrinter();
        menuPrinter.print(menu);
    }

}
