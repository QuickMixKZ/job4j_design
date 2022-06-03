package ru.job4j.design.lsp.parking;

public interface Parking {

    boolean isFreeSpace(Car car);

    boolean parkCar(Car car);

}
