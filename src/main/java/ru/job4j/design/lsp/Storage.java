package ru.job4j.design.lsp;


import java.util.List;

public interface Storage {

    boolean accept(Food food);

    boolean add(Food food);

    List<Food> getFoodList();
}
