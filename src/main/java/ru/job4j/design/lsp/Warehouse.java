package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return food.getUsage() < 25;
    }

    @Override
    public boolean add(Food food) {
        boolean result = accept(food);
        if (result) {
            foodList.add(food);
        }
        return result;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

}
