package ru.job4j.design.lsp;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Storage {

    boolean accept(Food food);

    boolean add(Food food);

    List<Food> getFoodList();

    default public int getUsage(Food food) {
        double totalDays = (double) ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        int remainDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), food.getExpiryDate());
        return Math.min((int) ((totalDays - remainDays) / totalDays * 100), 100);
    }
}
