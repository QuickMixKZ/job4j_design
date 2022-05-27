package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Storage> storages;

    public void sortProducts(List<Food> foodList) {
        for (Food food : foodList) {
            for (Storage storage : storages) {
                if (storage.add(food)) {
                    break;
                }
            }
        }
    }

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

}
