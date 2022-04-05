package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        boolean pause = false;
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        for (String userInput = scanner.next(); !userInput.equalsIgnoreCase(OUT); userInput = scanner.next()) {
            log.add(userInput);
            pause = pause ? !userInput.equalsIgnoreCase(CONTINUE) : userInput.equalsIgnoreCase(STOP);
            if (pause) {
                continue;
            }
            int index = getRandomNumber(phrases.size());
            String answer = phrases.get(index);
            System.out.println(answer);
            log.add(answer);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("botLog.txt", "botAnswers.txt");
        cc.run();
    }
}
