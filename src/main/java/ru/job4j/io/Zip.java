package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArgsName validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong amount of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        String directoryArg = argsName.get("d");
        Path root = Paths.get(directoryArg);
        if (!root.toFile().exists()) {
            throw new IllegalArgumentException("Directory not found.");
        }
        String excludeArg = argsName.get("e");
        if (!excludeArg.startsWith(".")) {
            throw new IllegalArgumentException("Wrong format of \"exclude\" argument.");
        }
        return argsName;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = zip.validateArgs(args);
        String directoryArg = argsName.get("d");
        String excludeArg = argsName.get("e");
        String outputArg = argsName.get("o");
        File outputFile = new File(outputArg);
        Path root = Paths.get(directoryArg);
        List<Path> sources = Search.search(root, path -> !path.endsWith(excludeArg));
        zip.packFiles(sources, outputFile);
    }
}