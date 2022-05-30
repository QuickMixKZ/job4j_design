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
        Food milk = new Milk("Простоквашино", LocalDate.now().plusDays(3623), LocalDate.now().minusDays(3), 500, 0);
        Food bread = new Bread("Простой хлеб", LocalDate.now().plusDays(2892), LocalDate.now().minusDays(3), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", LocalDate.now().plusDays(10258), LocalDate.now().minusDays(5), 100, 0);
        Food mineralWater = new Water("Минеральная вода",  LocalDate.now().plusDays(4256), LocalDate.now().minusDays(7), 150, 0);
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
        Food milk = new Milk("Простоквашино", LocalDate.now().plusDays(40), LocalDate.now().minusDays(149), 500, 0);
        Food bread = new Bread("Простой хлеб", LocalDate.now().plusDays(44), LocalDate.now().minusDays(30), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", LocalDate.now().plusDays(45), LocalDate.now().minusDays(30), 100, 0);
        Food mineralWater = new Water("Минеральная вода", LocalDate.now().plusDays(62), LocalDate.now().minusDays(30), 150, 0);
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
        Food milk = new Milk("Простоквашино", tomorrow, LocalDate.now().minusDays(144), 500, 10);
        Food bread = new Bread("Простой хлеб", tomorrow, LocalDate.now().minusDays(30), 40, 15);
        Food simpleWater = new Water("Прозрачная вода", tomorrow, LocalDate.now().minusDays(25), 100, 20);
        Food mineralWater = new Water("Минеральная вода", tomorrow, LocalDate.now().minusDays(28), 150, 30);
        Food discountedMilk = new Milk("Простоквашино", tomorrow, LocalDate.now().minusDays(144), 450, 10);
        Food discountedBread = new Bread("Простой хлеб", tomorrow, LocalDate.now().minusDays(30), 34, 15);
        Food discountedSimpleWater = new Water("Прозрачная вода", tomorrow, LocalDate.now().minusDays(25), 80, 20);
        Food discountedMineralWater = new Water("Минеральная вода", tomorrow, LocalDate.now().minusDays(28), 105, 30);
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
        Food milk = new Milk("Простоквашино", yesterday, LocalDate.now().minusDays(2), 500, 0);
        Food bread = new Bread("Простой хлеб", yesterday, LocalDate.now().minusDays(2), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", yesterday, LocalDate.now().minusDays(2), 100, 0);
        Food mineralWater = new Water("Минеральная вода", yesterday, LocalDate.now().minusDays(2), 150, 0);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(milk, bread, simpleWater, mineralWater), trash.getFoodList());
    }

    @Test
    public void whenSortedToEachStorage() {
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
        Food milk = new Milk("Простоквашино", LocalDate.now().plusDays(3623), LocalDate.now().minusDays(3), 500, 0);
        Food bread = new Bread("Простой хлеб", LocalDate.now().plusDays(44), LocalDate.now().minusDays(30), 40, 0);
        Food simpleWater = new Water("Прозрачная вода", LocalDate.now().plusDays(1), LocalDate.now().minusDays(25), 100, 20);
        Food mineralWater = new Water("Минеральная вода", yesterday, LocalDate.now().minusDays(2), 150, 0);
        Food discountedSimpleWater = new Water("Прозрачная вода", LocalDate.now().plusDays(1), LocalDate.now().minusDays(25), 80, 20);
        food.add(milk);
        food.add(bread);
        food.add(simpleWater);
        food.add(mineralWater);
        controlQuality.sortProducts(food);
        assertEquals(List.of(milk), warehouse.getFoodList());
        assertEquals(List.of(bread, discountedSimpleWater), shop.getFoodList());
        assertEquals(List.of(mineralWater), trash.getFoodList());
    }

}