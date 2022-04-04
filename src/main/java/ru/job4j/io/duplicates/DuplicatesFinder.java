package ru.job4j.io.duplicates;

import org.w3c.dom.Node;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        Map<FileProperty, List<Path>> files = duplicatesVisitor.getDuplicates();
        files.forEach((fileProperty, paths) -> paths.removeIf(path -> paths.size() < 2));
        files.forEach((fileProperty, paths) -> paths.forEach(System.out::println));
    }
}