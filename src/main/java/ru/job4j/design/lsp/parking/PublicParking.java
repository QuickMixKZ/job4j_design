package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class PublicParking implements Parking {

    private int passengerCarSpace;
    private int truckSpace;
    private final List<Car> storage;

    public PublicParking(int passengerCarSpace, int truckSpace) {
        this.passengerCarSpace = passengerCarSpace;
        this.truckSpace = truckSpace;
        storage = new ArrayList<>(passengerCarSpace + truckSpace);
    }

    @Override
    public boolean isFreeSpace(Car car) {
        boolean result = false;
        int carSize = car.getSize();
        if (carSize == 1 && passengerCarSpace != 0) {
            result = true;
            --passengerCarSpace;
        } else {
            if (truckSpace != 0) {
                result = true;
                --truckSpace;
            } else if (passengerCarSpace >= carSize) {
                result = true;
                passengerCarSpace -= carSize;
            }
        }
        return result;
    }

    @Override
    public boolean parkCar(Car car) {
        boolean result = isFreeSpace(car);
        if (result) {
            storage.add(car);
        }
        return result;
    }
}
