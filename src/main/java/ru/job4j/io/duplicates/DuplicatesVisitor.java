package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().isFile()) {
            FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
            if (files.containsKey(fileProperty)) {
                List<Path> paths = files.get(fileProperty);
                paths.add(file);
            } else {
                List<Path> tempList = new ArrayList<>();
                tempList.add(file);
                files.put(fileProperty, tempList);
            }
        }
        return super.visitFile(file, attrs);
    }

    Map<FileProperty, List<Path>> getDuplicates() {
        return files;
    }
}