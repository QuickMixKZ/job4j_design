package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            Pattern commentPattern = Pattern.compile("^#.*");
            Pattern configPattern = Pattern.compile(".*[^\\s]=[^\\s].*");
            Pattern keyValuePattern = Pattern.compile("^(.*?)=(.*)$");
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.isEmpty() || commentPattern.matcher(line).matches()) {
                    continue;
                }
                if (!configPattern.matcher(line).matches()) {
                    throw new IllegalArgumentException();
                }
                Matcher matcher = keyValuePattern.matcher(line);
                String key = null;
                String value = null;
                if (matcher.find()) {
                    key = matcher.group(1);
                    value = matcher.group(2);
                }
                values.put(key, value);
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