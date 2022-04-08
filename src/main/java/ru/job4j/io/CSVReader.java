package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    final private static String STDOUT = "stdout";

    public static ArgsName validateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong amount of arguments.");
        }
        ArgsName argsName = ArgsName.of(args);
        String path = argsName.get("path");
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found.");
        }
        return argsName;
    }

    private static void printResult(List<String> result, String outOption) {
        if (STDOUT.equals(outOption)) {
            result.forEach(System.out::println);
        } else {
            try (PrintWriter printWriter = new PrintWriter(outOption)) {
                result.forEach(printWriter::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void handle(ArgsName argsName) throws IOException {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<String> result = new ArrayList<>();
        result.add(filter.replaceAll(",", ";"));
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            String[] csvColumns = scanner.nextLine().split(delimiter);
            int index = 0;
            HashMap<String, Integer> columnsIndexes = new HashMap<>();
            for (String column: csvColumns) {
                columnsIndexes.put(column, index++);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arrayLine = line.split(delimiter);
                StringBuilder resultLine = new StringBuilder();
                for (String csvColumn : filter.split(",")) {
                    resultLine.append(arrayLine[columnsIndexes.get(csvColumn)]).append(delimiter);
                }
                result.add(resultLine.deleteCharAt(resultLine.length() - 1).toString());
            }
            printResult(result, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = validateArgs(args);
        CSVReader.handle(argsName);
    }
}
