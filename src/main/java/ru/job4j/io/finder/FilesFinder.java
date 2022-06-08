package ru.job4j.io.finder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.LogFilter;
import ru.job4j.io.Search;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilesFinder {

    public final static String MASK = "mask";
    public final static String NAME = "name";
    public final static String REGEX = "regex";

    public static void main(String[] args) throws IOException {
        FilesFinder filesFinder = new FilesFinder();
        ArgsName argsName = filesFinder.getArgs(args);
        Path root = Paths.get(argsName.get("d"));
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException("Wrong value of \"d\" argument.");
        }
        String name = argsName.get("n");
        String type = argsName.get("t");
        String output = argsName.get("o");
        Predicate<Path> condition = null;
        if (MASK.equals(type)) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + name);
            condition = matcher::matches;
        } else if (NAME.equals(type)) {
            condition = path -> path.toFile().getName().equals(name);
        } else if (REGEX.equals(type)) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + name);
            condition = matcher::matches;
        } else {
            throw new IllegalArgumentException("Wrong value of \"n\" argument.");
        }
        List<Path> paths = Search.search(root, condition);
        List<String> stringPaths = paths.stream()
                .map(Path::toString)
                .collect(Collectors.toList());
        LogFilter.save(stringPaths, output);
        stringPaths.forEach(System.out::println);
    }

    private ArgsName getArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        argsName.get("d");
        argsName.get("n");
        argsName.get("t");
        argsName.get("o");
        return argsName;
    }

}
