package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Argument not found.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments passed.");
        }
        Pattern argsPattern = Pattern.compile("-.*[^\\s]=[^\\s].*");
        Pattern keyValuePattern = Pattern.compile("(\\w*?)=(.*)");
        for (String arg : args) {
            if (!argsPattern.matcher(arg).matches()) {
                throw new IllegalArgumentException("Wrong argument's format.");
            }
            Matcher matcher = keyValuePattern.matcher(arg);
            String key = null;
            String value = null;
            if (matcher.find()) {
                key = matcher.group(1);
                value = matcher.group(2);
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}