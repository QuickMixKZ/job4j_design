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

    public static void main(String[] args) {
        List<Storage> storages = new ArrayList<>();
        storages.add(new Warehouse());
        storages.add(new Shop());
        storages.add(new Trash());
        ControlQuality controlQuality = new ControlQuality(storages);
        List<Food> food = new ArrayList<>();
        food.add(new Milk("Простоквашино", LocalDate.of(2022, 5, 1), LocalDate.of(2022, 1, 1), 500, 0));
        food.add(new Bread("Простой хлеб", LocalDate.of(2030, 5, 1), LocalDate.of(2022, 5, 1), 40, 0));
        food.add(new Water("Прозрачная вода", LocalDate.of(2022, 7, 1), LocalDate.of(2022, 5, 1), 100, 0));
        food.add(new Water("Минеральная вода", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 5, 1), 150, 0));
        controlQuality.sortProducts(food);
        storages.forEach(storage -> {
            System.out.println(storage.getFoodList());
        });
    }
}
