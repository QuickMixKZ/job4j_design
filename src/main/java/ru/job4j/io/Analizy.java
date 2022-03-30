package ru.job4j.io;

import java.io.*;
import java.util.regex.Pattern;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            Pattern patternStart = Pattern.compile("^[45]00.*");
            Pattern patternEnd = Pattern.compile("^[23]00.*");
            Pattern splitter = Pattern.compile(" ");
            boolean start = false;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!start && patternStart.matcher(line).matches()) {
                    String startTime = line.split(splitter.pattern())[1];
                    out.print(startTime + ";");
                    start = true;
                    continue;
                }
                if (start && patternEnd.matcher(line).matches()) {
                    String endTime = line.split(splitter.pattern())[1];
                    out.println(endTime + ";");
                    start = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/source.txt", "./data/target.txt");
    }
}
