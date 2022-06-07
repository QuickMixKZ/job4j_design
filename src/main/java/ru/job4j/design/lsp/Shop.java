package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean accept(Food food) {
        int usage = getUsage(food);
        return usage >= 25 && usage < 100;
    }

    @Override
    public boolean add(Food food) {
        boolean result = accept(food);
        if (result) {
            if (getUsage(food) >= 75) {
                setDiscountedPrice(food);
            }
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

    private void setDiscountedPrice(Food food) {
        double oldPrice = food.getPrice();
        double discount = food.getDiscount();
        if (discount != 0) {
            double newPrice = oldPrice - oldPrice * (discount / 100);
            food.setPrice(newPrice);
        }
    }

}
