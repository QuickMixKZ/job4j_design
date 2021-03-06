package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder and file extension is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        String folder = args[0];
        String extension = args[1];
        File folderFile = new File(folder);
        if (!folderFile.exists() && !folderFile.isDirectory()) {
            throw new IllegalArgumentException(String.format("There is no such directory - %s", folder));
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException(String.format("%s - are not extension.", folder));
        }
        Path start = Paths.get(folder);
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}