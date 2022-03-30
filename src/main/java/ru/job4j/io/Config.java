package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.isEmpty() || line.matches("^#.*")) {
                    continue;
                }
                if (!line.matches(".*[^\\s]=[^\\s].*")) {
                    throw new IllegalArgumentException();
                }
                values.put(line.split("=")[0], line.split("=")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String value(String key) {
        return values.get(key);
    }

}