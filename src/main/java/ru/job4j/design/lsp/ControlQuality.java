package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void sortProducts(List<Food> foodList) {
        for (Food food : foodList) {
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }

    public void resort() {
        List<Food> foodList = new ArrayList<>();
        for (Storage storage : storages) {
            foodList.addAll(storage.removeFood());
        }
        sortProducts(foodList);
    }
}
