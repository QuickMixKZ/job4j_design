package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenSortedToWarehouse() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        List<Food> food = new ArrayList<>();
        Food milk = new Milk("Простоквашино", LocalDate.of(2032, 5, 1), LocalDate.of(2022, 1, 1), 500, 0);
        Food bread = new Bread("Простой хлеб", LocalDate.of(2030, 5, 1), LocalDate.of(2022, 5, 1), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", LocalDate.of(2050, 7, 1), LocalDate.of(2022, 5, 1), 100, 0);
        Food mineralWater = new Water("Минеральная вода", LocalDate.of(2065, 6, 1), LocalDate.of(2022, 5, 1), 150, 0);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(milk, bread, simpleWater, mineralWater), warehouse.getFoodList());
    }

    @Test
    public void whenSortedToShop() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        List<Food> food = new ArrayList<>();
        Food milk = new Milk("Простоквашино", LocalDate.of(2022, 7, 10), LocalDate.of(2022, 1, 1), 500, 0);
        Food bread = new Bread("Простой хлеб", LocalDate.of(2022, 7, 14), LocalDate.of(2022, 5, 1), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", LocalDate.of(2022, 7, 1), LocalDate.of(2022, 5, 1), 100, 0);
        Food mineralWater = new Water("Минеральная вода", LocalDate.of(2022, 8, 1), LocalDate.of(2022, 5, 1), 150, 0);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(milk, bread, simpleWater, mineralWater), shop.getFoodList());
    }

    @Test
    public void whenSortedToShopAndDiscounted() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        List<Food> food = new ArrayList<>();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Food milk = new Milk("Простоквашино", tomorrow, LocalDate.of(2022, 1, 1), 500, 0);
        Food bread = new Bread("Простой хлеб", tomorrow, LocalDate.of(2022, 5, 1), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", tomorrow, LocalDate.of(2022, 5, 1), 100, 0);
        Food mineralWater = new Water("Минеральная вода", tomorrow, LocalDate.of(2022, 5, 1), 150, 0);
        Food discountedMilk = new Milk("Простоквашино", tomorrow, LocalDate.of(2022, 1, 1), 500, 10);
        Food discountedBread = new Bread("Простой хлеб", tomorrow, LocalDate.of(2022, 5, 1), 40, 10);
        Food discountedSimpleWater = new Water("Прозрачная вода", tomorrow, LocalDate.of(2022, 5, 1), 100, 10);
        Food discountedMineralWater = new Water("Минеральная вода", tomorrow, LocalDate.of(2022, 5, 1), 150, 10);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(discountedMilk, discountedBread, discountedSimpleWater, discountedMineralWater), shop.getFoodList());
    }

    @Test
    public void whenSortedToTrash() {
        Storage warehouse = new Warehouse();
        Storage shop = new Shop();
        Storage trash = new Trash();
        List<Storage> storages = new ArrayList<>();
        storages.add(warehouse);
        storages.add(shop);
        storages.add(trash);
        ControlQuality controlQuality = new ControlQuality(storages);
        List<Food> food = new ArrayList<>();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Food milk = new Milk("Простоквашино", yesterday, LocalDate.of(2022, 1, 1), 500, 0);
        Food bread = new Bread("Простой хлеб", yesterday, LocalDate.of(2022, 5, 1), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", yesterday, LocalDate.of(2022, 5, 1), 100, 0);
        Food mineralWater = new Water("Минеральная вода", yesterday, LocalDate.of(2022, 5, 1), 150, 0);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(milk, bread, simpleWater, mineralWater), trash.getFoodList());
    }

}