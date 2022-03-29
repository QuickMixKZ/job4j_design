package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public void writeToFile() {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i <= 10; i++) {
                StringBuilder line = new StringBuilder("");
                for (int j = 1; j <= 10; j++) {
                    line.append(i * j);
                    line.append(" ");
                }
                line.append(System.lineSeparator());
                out.write(line.toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
