package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Emulator {

    public static final String LOAD = "Load";
    public static final String GET = "Get";
    public static final String EXIT = "Exit";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            File file;
            String input;
            boolean run;
            do {
                System.out.println("Enter directory address: (or \"Exit\")");
                input = reader.readLine();
                file = new File(input);
                run = !input.equalsIgnoreCase(EXIT);
            } while (!file.isDirectory() && run);
            AbstractCache<String, String> cache = new DirFileCache(input);
            while (run) {
                System.out.println("Chose action:"
                        + System.lineSeparator()
                        + "*Load"
                        + System.lineSeparator()
                        + "*Get"
                        + System.lineSeparator()
                        + "*Exit");
                input = reader.readLine();
                if (input.equalsIgnoreCase(LOAD)) {
                    System.out.println("Enter key:");
                    input = reader.readLine();
                    cache.put(input, cache.load(input));
                } else if (input.equalsIgnoreCase(GET)) {
                    System.out.println("Enter key:");
                    input = reader.readLine();
                    System.out.println(cache.get(input));
                } else if (input.equalsIgnoreCase(EXIT)) {
                    run = false;
                } else {
                    System.out.println("Chose correct action.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}









