package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {

    private static void showDuplicates(Map<FileProperty, List<Path>> files) {
        files.forEach((fileProperty, paths) -> paths.stream().filter(path -> paths.size() > 1).forEach(System.out::println));
    }

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        showDuplicates(duplicatesVisitor.getDuplicates());
    }
}