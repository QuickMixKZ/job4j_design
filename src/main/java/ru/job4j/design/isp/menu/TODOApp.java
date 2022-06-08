package ru.job4j.design.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean run = true;
            IndentMenuPrinter menuPrinter = new IndentMenuPrinter();
            while (run) {
                System.out.println("Chose action:"
                        + System.lineSeparator()
                        + "N - add new task."
                        + System.lineSeparator()
                        + "S - show tasks."
                        + System.lineSeparator()
                        + "E - exit");
                String input = reader.readLine();
                if (input.equalsIgnoreCase("N")) {
                    System.out.println("Print parent element name:");
                    String parent = reader.readLine();
                    System.out.println("Print child element name:");
                    String child = reader.readLine();
                    if (!menu.add(parent.isEmpty() ? Menu.ROOT : parent, child, STUB_ACTION)) {
                        System.out.println("No such parent element!");
                    }
                } else if (input.equalsIgnoreCase("S")) {
                    menuPrinter.print(menu);
                } else if (input.equalsIgnoreCase("E")) {
                    run = false;
                } else {
                    System.out.println("No such action. Try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
