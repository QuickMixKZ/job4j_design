package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        return getUsage(food) < 25;
    }

    @Override
    public boolean add(Food food) {
        boolean result = accept(food);
        if (result) {
            foodList.add(food);
        }
        return result;
    }

    @Override
    public List<Food> getFoodList() {
        return new ArrayList<>(foodList);
    }

    @Override
    public List<Food> removeFood() {
        List<Food> newFoodList = getFoodList();
        foodList.removeAll(newFoodList);
        return newFoodList;
    }

}
